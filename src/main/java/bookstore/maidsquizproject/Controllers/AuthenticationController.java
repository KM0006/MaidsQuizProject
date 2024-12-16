package bookstore.maidsquizproject.Controllers;

import bookstore.maidsquizproject.Services.LibrarianServices;
import org.springframework.web.bind.annotation.*;
import bookstore.maidsquizproject.Models.Librarian;
import bookstore.maidsquizproject.Models.ApiResponse;
import bookstore.maidsquizproject.Services.JwtServices;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.DataTransferObjects.LibrarianDto;

@RestController
public class AuthenticationController
{

	LibrarianServices LibrarianServices;

	JwtServices jwtServices;

	@Autowired
	public AuthenticationController(JwtServices jwtServices, LibrarianServices LibrarianServices)
	{
		this.LibrarianServices = LibrarianServices;
		this.jwtServices = jwtServices;
	}

	@PostMapping("/login")
	public ApiResponse<String> Login(@RequestBody LibrarianDto librarianDto)
	{
		return new ApiResponse<>(jwtServices.GenerateToken(new Librarian(librarianDto)));
	}

}
