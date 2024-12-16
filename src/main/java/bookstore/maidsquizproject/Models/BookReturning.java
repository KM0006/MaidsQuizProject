package bookstore.maidsquizproject.Models;

import java.sql.Date;

public class BookReturning
{
	public int BorrowingBook;
	public int Librarian;
	public Date ReturningDate;

	public BookReturning() {}

	public BookReturning(int borrowingBook, int librarian, Date returningDate)
	{
		BorrowingBook = borrowingBook;
		Librarian = librarian;
		ReturningDate = returningDate;
	}
}
