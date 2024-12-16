package bookstore.maidsquizproject.Models;

import java.sql.Date;

public class BookBorrowingStateVw
{
	public int Id;

	public int Book;

	public int Patron;

	public Date BorrowingDate;

	public Date ReturningDate;

	public BookBorrowingStateVw() {}

	public BookBorrowingStateVw(int id, int book, int patron, Date borrowingDate, Date returningDate)
	{
		Id = id;
		Book = book;
		Patron = patron;
		BorrowingDate = borrowingDate;
		ReturningDate = returningDate;
	}

	public BookBorrowingStateVw(int book, int patron, Date borrowingDate, Date returningDate)
	{
		Book = book;
		Patron = patron;
		BorrowingDate = borrowingDate;
		ReturningDate = returningDate;
	}
}
