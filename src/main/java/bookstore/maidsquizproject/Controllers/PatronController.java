package bookstore.maidsquizproject.Controllers;

import org.springframework.web.bind.annotation.*;
import bookstore.maidsquizproject.Models.Patron;
import bookstore.maidsquizproject.Models.ApiResponse;
import bookstore.maidsquizproject.Services.PatronServices;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.DataTransferObjects.PatronDto;

import java.util.List;

@RestController
public class PatronController
{
	PatronServices patronServices;

	@Autowired
	public PatronController(PatronServices patronService)
	{
		this.patronServices = patronService;
	}

	@GetMapping("/getAllPatrons")
	public ApiResponse<List<Patron>> getAllPatrons()
	{
		return new ApiResponse<>(patronServices.PatronList());
	}

	@GetMapping("/getActivePatrons")
	public ApiResponse<List<Patron>> getActivePatrons()
	{
		return new ApiResponse<>(patronServices.PatronActiveList());
	}

	@GetMapping("/getPatron/{Id}")
	public ApiResponse<Patron> getPatronById(@PathVariable int Id)
	{
		return new ApiResponse<>( patronServices.PatronGetById(Id));
	}

	@PostMapping("/addPatron")
	public ApiResponse<String> addPatron(@RequestBody PatronDto patronDto)
	{
		patronServices.PatronInsert(new Patron(patronDto));
		return new ApiResponse<>("Patron Added successfully");
	}

	@PostMapping("/updatePatron")
	public ApiResponse<String> updatePatron(@RequestBody Patron patron)
	{
		patronServices.PatronUpdate(patron);
		return new ApiResponse<>("Patron Updated successfully");
	}

	@GetMapping("/deletePatron/{Id}")
	public ApiResponse<String> deletePatron(@PathVariable int Id)
	{
		patronServices.PatronDelete(Id);
		return new ApiResponse<>("Patron Updated successfully");
	}
}
