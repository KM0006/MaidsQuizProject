package bookstore.maidsquizproject.Repositories;

import bookstore.maidsquizproject.Models.Book;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class BookRepository extends JdbcDaoSupport
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookRepository(DataSource DataSource)
	{
		super.setDataSource(DataSource);
		this.jdbcTemplate = getJdbcTemplate();
	}

	public Book BookGetById(int Id)
	{
		return jdbcTemplate.query(this.BookGetById, new BookRowMapper(), Id).getFirst();
	}

	public Book BookGetByIsbn10(String Isbn10)
	{
		return jdbcTemplate.query(this.BookGetByIsbn10, new BookRowMapper(), Isbn10).getFirst();
	}

	public List<Book> BookList()
	{
		return jdbcTemplate.query(this.BookList, new BookRowMapper());
	}

	public List<Book> BookActiveList()
	{
		return jdbcTemplate.query(this.BookActiveList, new BookRowMapper());
	}

	public void BookInsert(Book book)
	{
		jdbcTemplate.query(this.BookInsert, (ResultSetExtractor<Void>) _ -> null, book.Title, book.Isbn10, book.PublicationDate);
	}

	public void BookUpdate(Book book)
	{
		jdbcTemplate.query(this.BookUpdate, (ResultSetExtractor<Void>) _ -> null, book.Id, book.Title, book.Isbn10, book.PublicationDate, book.IsActive);
	}

	public void BookDelete(int Id)
	{
		jdbcTemplate.query(this.BookDelete, (ResultSetExtractor<Void>) _ -> null, Id);
	}



	public static class BookRowMapper implements RowMapper<Book>
	{
		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new Book
			(
				rs.getInt("Id"),
				rs.getString("Title"),
				rs.getString("Isbn10"),
				rs.getDate("PublicationDate"),
				rs.getBoolean("IsActive")
			);
		}
	}

	private final String BookGetById = "call BookStore.BookGetById(?);";

	private final String BookGetByIsbn10 = "call BookStore.BookGetByIsbn10(?);";

	private final String BookList = "call BookStore.BookList();";

	private final String BookActiveList = "call BookStore.BookActiveList();";

	private final String BookInsert = "call BookStore.BookInsert(?, ?, ?, @dummy);";

	private final String BookUpdate = "call BookStore.BookUpdate(?, ?, ?, ?, ?);";

	private final String BookDelete = "call BookStore.BookDelete(?);";

}
