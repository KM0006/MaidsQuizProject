package bookstore.maidsquizproject.Services;

import bookstore.maidsquizproject.Exceptions.InconsistentBookReturningDateException;
import bookstore.maidsquizproject.Models.BookBorrowingStateVw;
import bookstore.maidsquizproject.Models.BookReturning;
import org.springframework.stereotype.Service;
import bookstore.maidsquizproject.Models.BookBorrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import bookstore.maidsquizproject.Repositories.BookBorrowingProcessRepository;

import java.sql.Date;

@Service
public class BookBorrowingProcessServices
{
	private final BookBorrowingProcessRepository bookBorrowingProcessRepository;

	@Autowired
	public BookBorrowingProcessServices(BookBorrowingProcessRepository bookBorrowingProcessRepository)
	{
		this.bookBorrowingProcessRepository = bookBorrowingProcessRepository;
	}

	public void BookBorrowingInsert(BookBorrowing bookBorrowing)
	{
		bookBorrowingProcessRepository.BookBorrowingInsert(bookBorrowing);
	}

	@Transactional
	public void BookReturningInsert(int Book, int Patron, int Librarian, Date ReturningDate) throws InconsistentBookReturningDateException
	{

		BookBorrowingStateVw bookBorrowingStateVw = this.BookBorrowingGetLatestByBookPatronTuple(Book, Patron);

		if (ReturningDate.before(bookBorrowingStateVw.BorrowingDate))
		{
			throw new InconsistentBookReturningDateException();
		}

		this.BookReturningInsert(new BookReturning(bookBorrowingStateVw.Id, Librarian, ReturningDate));

	}

	public void BookReturningInsert(BookReturning bookReturning)
	{
		bookBorrowingProcessRepository.BookReturningInsert(bookReturning);
	}


	public BookBorrowingStateVw BookBorrowingGetLatestByBookPatronTuple(int Book, int Patron)
	{

		return bookBorrowingProcessRepository.BookBorrowingGetLatestByBookPatronTuple(Book, Patron);
	}

}
