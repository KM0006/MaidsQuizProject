package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.LibrarianDto;

public class Librarian
{

	public int Id;

	public String UserName;

	public String UserPassword;

	public Librarian(int id, String userName, String userPassword)
	{
		Id = id;
		UserName = userName;
		UserPassword = userPassword;
	}

	public Librarian(LibrarianDto librarianDto)
	{
		UserName = librarianDto.UserName;
		UserPassword = librarianDto.UserPassword;
	}

	public Librarian(String userName, String userPassword)
	{
		UserName = userName;
		UserPassword = userPassword;
	}

}
