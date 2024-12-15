package bookstore.maidsquizproject.Controllers;

import bookstore.maidsquizproject.DataTransferObjects.AuthorDto;
import bookstore.maidsquizproject.Models.ApiResponse;
import bookstore.maidsquizproject.Models.Author;
import bookstore.maidsquizproject.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController
{

	private final AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService)
	{
		this.authorService = authorService;
	}

	@GetMapping("/getAllAuthors")
	public ApiResponse<List<Author>> getAllAuthors()
	{
		return new ApiResponse<>(authorService.AuthorList());
	}

	@PostMapping("/addAuthor")
	public ApiResponse<String> addAuthor(@RequestBody AuthorDto authorDto)
	{
		authorService.AuthorInsert(new Author(authorDto));
		return new ApiResponse<>("Author Added Successfully");
	}

	@PostMapping("/updateAuthor")
	public ApiResponse<String> updateAuthor(@RequestBody Author author)
	{
		authorService.AuthorUpdate(author);
		return new ApiResponse<>("Author Updated Successfully");
	}

}
