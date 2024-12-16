package bookstore.maidsquizproject.Repositories;

import bookstore.maidsquizproject.Models.Book;
import bookstore.maidsquizproject.Models.BookBorrowing;
import bookstore.maidsquizproject.Models.BookBorrowingStateVw;
import bookstore.maidsquizproject.Models.BookReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class BookBorrowingProcessRepository
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookBorrowingProcessRepository(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public void BookBorrowingInsert(BookBorrowing bookBorrowing)
	{
		jdbcTemplate.query(this.BookBorrowingInsert, (ResultSetExtractor<Void>) _ -> null, bookBorrowing.Book, bookBorrowing.Patron, bookBorrowing.Librarian, bookBorrowing.BorrowingDate);
	}

	public void BookReturningInsert(BookReturning bookReturning)
	{
		jdbcTemplate.query(this.BookReturningInsert, (ResultSetExtractor<Void>) _ -> null, bookReturning.BorrowingBook, bookReturning.Librarian, bookReturning.ReturningDate);
	}

	public BookBorrowingStateVw BookBorrowingGetLatestByBookPatronTuple(int Book, int Patron)
	{
		return jdbcTemplate.query(this.BookBorrowingGetLatestByBookPatronTuple, new BookBorrowingStateVwRowMapper(), Book, Patron).getFirst();
	}

	public static class BookBorrowingStateVwRowMapper implements RowMapper<BookBorrowingStateVw>
	{

		@Override
		public BookBorrowingStateVw mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new BookBorrowingStateVw
			(
				rs.getInt("Id"),
				rs.getInt("Book"),
				rs.getInt("Patron"),
				rs.getDate("BorrowingDate"),
				rs.getDate("ReturningDate")
			);
		}
	}

	private final String BookBorrowingInsert = "call BookStore.BookBorrowingInsert(?, ?, ?, ?, @dummy)";

	private final String BookReturningInsert = "call BookStore.BookReturningInsert(?, ?, ?)";

	private final String BookBorrowingGetLatestByBookPatronTuple = "call BookStore.BookBorrowingGetLatestByBookPatronTuple(?, ?)";

}
