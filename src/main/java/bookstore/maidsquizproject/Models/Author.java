package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.AuthorDto;

public class Author
{
	public int Id;
	public String FirstName;
	public String LastName;

	public Author() {}

	public Author(int id, String firstName, String lastName)
	{
		Id = id;
		FirstName = firstName;
		LastName = lastName;
	}

	public Author(AuthorDto authorDto)
	{
		FirstName = authorDto.FirstName;
		LastName = authorDto.LastName;
	}

	public Author(String firstName, String lastName)
	{
		FirstName = firstName;
		LastName = lastName;
	}
}
