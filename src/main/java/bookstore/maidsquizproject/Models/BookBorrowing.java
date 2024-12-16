package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.BookBorrowingDto;

import java.sql.Date;

public class BookBorrowing
{
	public int Id;

	public int Book;

	public int Patron;

	public int Librarian;

	public Date BorrowingDate;

	public BookBorrowing() {}

	public BookBorrowing(BookBorrowingDto bookBorrowingDto)
	{
		Book = bookBorrowingDto.Book;
		Librarian = bookBorrowingDto.Librarian;
		Patron = bookBorrowingDto.Patron;
		BorrowingDate = bookBorrowingDto.BorrowingDate;
	}

	public BookBorrowing(int id, int book, int librarian, int patron, Date borrowingDate)
	{
		Id = id;
		Book = book;
		Librarian = librarian;
		Patron = patron;
		BorrowingDate = borrowingDate;
	}

	public BookBorrowing(int book, int librarian, int patron, Date borrowingDate)
	{
		Book = book;
		Librarian = librarian;
		Patron = patron;
		BorrowingDate = borrowingDate;
	}

}
