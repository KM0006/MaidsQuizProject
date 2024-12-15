package bookstore.maidsquizproject.Repositories;

import bookstore.maidsquizproject.Models.Book;
import bookstore.maidsquizproject.Models.Author;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import bookstore.maidsquizproject.Models.BookAuthor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class BookAuthorRepository
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookAuthorRepository(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public void BookAuthorInsert(BookAuthor bookAuthor)
	{
		jdbcTemplate.query(this.BookAuthorInsert, (ResultSetExtractor<Void>) _ -> null, bookAuthor.Book, bookAuthor.Author);
	}

	public List<Author> BookAuthorGetAuthorListByBook(int Book)
	{
		return jdbcTemplate.query(this.BookAuthorGetAuthorListByBook, new AuthorRepository.AuthorRowMapper(), Book);
	}

	public List<Book> BookAuthorGetBookListByAuthor(int Author)
	{
		return jdbcTemplate.query(this.BookAuthorGetBookListByAuthor, new BookRepository.BookRowMapper(), Author);
	}

	private final String BookAuthorInsert = "call BookStore.BookAuthorInsert(?, ?, @dummy);";

	private final String BookAuthorGetAuthorListByBook = "call BookStore.BookAuthorGetAuthorListByBook(?);";

	private final String BookAuthorGetBookListByAuthor = "call BookStore.BookAuthorGetBookListByAuthor(?);";

}
