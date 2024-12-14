package bookstore.maidsquizproject.Controllers;

import java.util.List;

import bookstore.maidsquizproject.DataTransferObjects.BookDto;
import bookstore.maidsquizproject.Models.Book;
import bookstore.maidsquizproject.Models.ApiResponse;
import bookstore.maidsquizproject.Services.BookServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController
{

	BookServices bookServices;

	@Autowired
	public BookController(BookServices bookService)
	{
		this.bookServices = bookService;
	}

	@GetMapping("/getAllBooks")
	public ApiResponse<List<Book>> getAllBooks()
	{
		return new ApiResponse<>(bookServices.BookList());
	}

	@GetMapping("/getActiveBooks")
	public ApiResponse<List<Book>> getActiveBooks()
	{
		return new ApiResponse<>(bookServices.BookActiveList());
	}

	@GetMapping("/getBook/{Id}")
	public ApiResponse<Book> getBookById(@PathVariable int Id)
	{
		return new ApiResponse<>( bookServices.BookGetById(Id));
	}

	@GetMapping("/getBookByIsbn/Isbn={Isbn}")
	public ApiResponse<Book> getBookByIsbn10(@PathVariable String Isbn)
	{
		return new ApiResponse<>(bookServices.BookGetByIsbn10(Isbn));
	}

	@PostMapping("/addBook")
	public ApiResponse<String> addBook(@RequestBody BookDto bookDto)
	{
		bookServices.BookInsert(new Book(bookDto));
		return new ApiResponse<>("Book Added successfully");
	}

	@PostMapping("/updateBook")
	public ApiResponse<String> updateBook(@RequestBody Book book)
	{
		bookServices.BookUpdate(book);
		return new ApiResponse<>("Book Updated successfully");
	}

	@PostMapping("/deleteBook/{Id}")
	public ApiResponse<String> deleteBook(@PathVariable int Id)
	{
		bookServices.BookDelete(Id);
		return new ApiResponse<>("Book Updated successfully");
	}

}

