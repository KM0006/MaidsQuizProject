package bookstore.maidsquizproject.Services;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

	public List<Book> BookAvailableList()
	{
		return bookRepository.BookAvailableList();
	}

	public List<Book> BookBorrowedList()
	{
		return bookRepository.BookBorrowedList();
	}

	public Book BookGetById(int Id)
	{

		Book book = bookRepository.BookGetById(Id);

		if (book == null)
		{
			throw new ResourceNotFoundException();
		}
		return book;

	}

	public Book BookGetByIsbn10(String Isbn10)
	{

		Book book = bookRepository.BookGetByIsbn10(Isbn10);

		if (book == null)
		{
			throw new ResourceNotFoundException();
		}

		return book;

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
