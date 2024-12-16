package bookstore.maidsquizproject.Controllers;

import org.springframework.web.bind.annotation.*;
import bookstore.maidsquizproject.Models.Librarian;
import bookstore.maidsquizproject.Models.ApiResponse;
import bookstore.maidsquizproject.Models.BookBorrowing;
import bookstore.maidsquizproject.Services.LibrarianServices;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.DataTransferObjects.BookBorrowingDto;
import bookstore.maidsquizproject.Services.BookBorrowingProcessServices;
import bookstore.maidsquizproject.Exceptions.InconsistentBookReturningDateException;

@RestController
public class BookBorrowingProcessController
{

	private final BookBorrowingProcessServices bookBorrowingProcessServices;

	private final LibrarianServices librarianServices;

	@Autowired
	public BookBorrowingProcessController(BookBorrowingProcessServices bookBorrowingProcessServices, LibrarianServices librarianServices)
	{
		this.bookBorrowingProcessServices = bookBorrowingProcessServices;
		this.librarianServices = librarianServices;
	}

	@PostMapping("/addBookBorrowing")
	public ApiResponse<String> addBookBorrowing(@RequestBody BookBorrowingDto bookBorrowingDto, @RequestHeader String Authorization)
	{

		BookBorrowing bookBorrowing = new BookBorrowing();

		bookBorrowing.Book = bookBorrowingDto.Book;

		bookBorrowing.Patron = bookBorrowingDto.Patron;

		bookBorrowing.BorrowingDate = bookBorrowingDto.BorrowingDate;

		bookBorrowing.Librarian = this.GetLibrarianIdFromBearerToken(Authorization);

		bookBorrowingProcessServices.BookBorrowingInsert(bookBorrowing);

		return new ApiResponse<>("Book Borrowing Record Added Successfully");

	}

	@PutMapping("/addBookReturning/{Book}/patron/{Patron}")
	public ApiResponse<String> addBookReturning(@PathVariable int Book, @PathVariable int Patron, @RequestHeader String Authorization) throws InconsistentBookReturningDateException
	{

		int Librarian = this.GetLibrarianIdFromBearerToken(Authorization);

		java.util.Date date = new java.util.Date();

		bookBorrowingProcessServices.BookReturningInsert(Book, Patron, Librarian, new java.sql.Date(date.getTime()));

		return new ApiResponse<>("Book returned Successfully");

	}

	private int GetLibrarianIdFromBearerToken(String Authorization)
	{

		String Token = Authorization.substring(7);

		Librarian librarian = librarianServices.LibrarianGetByBearerToken(Token);

		return librarian.Id;

	}

}
