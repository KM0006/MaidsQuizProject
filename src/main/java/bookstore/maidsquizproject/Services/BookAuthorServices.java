package bookstore.maidsquizproject.Services;

import bookstore.maidsquizproject.Models.Author;
import bookstore.maidsquizproject.Models.Book;
import bookstore.maidsquizproject.Models.BookAuthor;
import bookstore.maidsquizproject.Repositories.BookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorServices
{
	private final BookAuthorRepository bookAuthorRepository;

	@Autowired
	public BookAuthorServices(BookAuthorRepository bookAuthorRepository)
	{
		this.bookAuthorRepository = bookAuthorRepository;
	}

	public void BookAuthorInsert(BookAuthor bookAuthor)
	{
		bookAuthorRepository.BookAuthorInsert(bookAuthor);
	}

	public List<Author> BookAuthorGetAuthorListByBook(int Book)
	{
		return bookAuthorRepository.BookAuthorGetAuthorListByBook(Book);
	}

	public List<Book> BookAuthorGetBookListByAuthor(int Author)
	{
		return bookAuthorRepository.BookAuthorGetBookListByAuthor(Author);
	}

}
