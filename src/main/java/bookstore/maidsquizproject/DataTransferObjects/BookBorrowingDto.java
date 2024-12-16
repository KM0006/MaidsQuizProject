package bookstore.maidsquizproject.DataTransferObjects;

import java.sql.Date;

public class BookBorrowingDto
{
	public int Book;

	public int Patron;

	public int Librarian;

	public Date BorrowingDate;

	public BookBorrowingDto(int book, int librarian, int patron, Date borrowingDate)
	{
		Book = book;
		Librarian = librarian;
		Patron = patron;
		BorrowingDate = borrowingDate;
	}
}
