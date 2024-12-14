package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.PatronDto;

public class Patron
{

	public int Id;

	public String FirstName;

	public String LastName;

	public String Email;

	public String PhoneNumber;

	public boolean IsActive;

	public Patron() {}

	public Patron(PatronDto patronDto)
	{
		FirstName = patronDto.FirstName;
		LastName = patronDto.LastName;
		Email = patronDto.Email;
		PhoneNumber = patronDto.PhoneNumber;
		IsActive = patronDto.IsActive;
	}

	public Patron(int id, String firstName, String lastName, String email, String phoneNumber, boolean isActive)
	{
		Id = id;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		IsActive = isActive;
	}



	public Patron(String firstName, String lastName, String email, String phoneNumber, boolean isActive)
	{
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		IsActive = isActive;
	}

}
