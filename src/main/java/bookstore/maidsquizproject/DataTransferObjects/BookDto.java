package bookstore.maidsquizproject.DataTransferObjects;

import java.sql.Date;

public class BookDto
{

	public String Title;

	public String Isbn10;

	public Date PublicationDate;

	public boolean IsActive;

	public BookDto(String title, String isbn10, Date publicationDate, boolean isActive)
	{
		Title = title;
		Isbn10 = isbn10;
		PublicationDate = publicationDate;
		IsActive = isActive;
	}

}
