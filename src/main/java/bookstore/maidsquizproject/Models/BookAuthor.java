package bookstore.maidsquizproject.Models;

import bookstore.maidsquizproject.DataTransferObjects.BookAuthorDto;

public class BookAuthor
{

	public int Id;

	public int Book;

	public int Author;

	public BookAuthor() {}


	public BookAuthor(BookAuthorDto bookAuthorDto)
	{
		Book = bookAuthorDto.Book;
		Author = bookAuthorDto.Author;
	}

	public BookAuthor(int book, int author)
	{
		Book = book;
		Author = author;
	}


	public BookAuthor(int id, int book, int author)
	{
		Id = id;
		Book = book;
		Author = author;
	}
}
