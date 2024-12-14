package bookstore.maidsquizproject.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import bookstore.maidsquizproject.Models.Book;
import bookstore.maidsquizproject.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookServices
{

	private final BookRepository bookRepository;

	@Autowired
	public BookServices(BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}

	public List<Book> BookList()
	{
		return bookRepository.BookList();
	}

	public List<Book> BookActiveList()
	{
		return bookRepository.BookActiveList();
	}


	public Book BookGetById(int Id)
	{
		return bookRepository.BookGetById(Id);
	}

	public Book BookGetByIsbn10(String Isbn10)
	{
		return bookRepository.BookGetByIsbn10(Isbn10);
	}

	public void BookInsert(Book book)
	{
		bookRepository.BookInsert(book);
	}

	public void BookUpdate(Book book)
	{
		bookRepository.BookUpdate(book);
	}

	public void BookDelete(int Id)
	{
		bookRepository.BookDelete(Id);
	}

}
