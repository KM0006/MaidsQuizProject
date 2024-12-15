package bookstore.maidsquizproject.Controllers;

import bookstore.maidsquizproject.Models.Author;
import bookstore.maidsquizproject.Models.Book;
import org.springframework.web.bind.annotation.*;
import bookstore.maidsquizproject.Models.BookAuthor;
import bookstore.maidsquizproject.Models.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.Services.BookAuthorServices;
import bookstore.maidsquizproject.DataTransferObjects.BookAuthorDto;

import java.util.List;

@RestController
public class BookAuthorController
{

	private final BookAuthorServices bookAuthorServices;

	@Autowired
	public BookAuthorController(BookAuthorServices bookAuthorServices)
	{
		this.bookAuthorServices = bookAuthorServices;
	}

	@GetMapping("/getBookAuthors/{Book}")
	public ApiResponse<List<Author>> getBookAuthors(@PathVariable int Book)
	{
		return new ApiResponse<>(bookAuthorServices.BookAuthorGetAuthorListByBook(Book));
	}

	@GetMapping("/getAuthorBooks/{Author}")
	public ApiResponse<List<Book>> getAuthorBooks(@PathVariable int Author)
	{
		return new ApiResponse<>(bookAuthorServices.BookAuthorGetBookListByAuthor(Author));
	}

	@PostMapping("/addBookAuthor")
	public ApiResponse<String> addBookAuthor(@RequestBody BookAuthorDto bookAuthorDto)
	{
		bookAuthorServices.BookAuthorInsert(new BookAuthor(bookAuthorDto));
		return new ApiResponse<>("BookAuthor Added Successfully");
	}

}
