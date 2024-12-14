package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.BookDto;

import java.sql.Date;

public class Book
{

	public int Id;

	public String Title;

	public String Isbn10;

	public Date PublicationDate;

	public boolean IsActive;

	public Book(String title, String isbn10, Date publicationDate, boolean isActive)
	{
		Title = title;
		Isbn10 = isbn10;
		PublicationDate = publicationDate;
		IsActive = isActive;
	}

	public Book() {}

	public Book(int id, String title, String isbn10, Date publicationDate, boolean isActive)
	{
		Id = id;
		Title = title;
		Isbn10 = isbn10;
		PublicationDate = publicationDate;
		IsActive = isActive;
	}

	public Book(BookDto bookDto)
	{
		Title = bookDto.Title;
		Isbn10 = bookDto.Isbn10;
		PublicationDate = bookDto.PublicationDate;
		IsActive = bookDto.IsActive;
	}

}
