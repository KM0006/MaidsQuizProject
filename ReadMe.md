
# BookStore Application

## Project Structure:

- Main App Runner: MaidsQuizProjectApplication

- Controllers: Routing Api endpoint receiving Dto representing data delivered.

- Services: Services related to basic entities and Business logic implemented.

- Repositories: Repositories concerned with database connection and SQL queries execution with dedicated RowMappers.

- Models & Dto: Models represent basic entities in Database like tables and views (with suffix Vw),
  whereas Dto is an optional Data transfer objects designed only to hold information in a structure complied with the developer's vision
  about the GUI interacting with the Backend, Example Below.
  > BookBorrowingDto holds information regarding BookId and PatronId only, because from the developer perspective,
  > there supposed to be a GUI viewing a form with the available books and registered patrons in the system.

- Exceptions: Custom Exceptions in the Backend, with appropriate messages.

- Utils: a package designated to provide configuration like security and application.properties secured values.

Below a table regarding provided api and a small details regarding them.

|                   Api                    |                                                       Description                                                       |                         Required Data                          |              Returned Data               |
|:----------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------:|:----------------------------------------:|
|                  /login                  |                                           Librarian logs in with credentials                                            |             LibrarianDto (UserName, UserPassword)              |    Jwt Token for later authorization     |
|                                          |                                                                                                                         |                                                                |                                          |
|              /getAllAuthors              |                                     Return List of Authors Registered in the system                                     |                               /                                | List of Authors Registered in the system |
|                /addAuthor                |                                               Add an Author to the system                                               |                AuthorDto (First and Last Name)                 |       Message("Added Successfully)       |
|              /updateAuthor               |                                        update an author registered in the system                                        |                      Author updated Data                       |     Message("Updated Successfully")      |
|                                          |                                                                                                                         |                                                                |                                          |
|          /getBookAuthors/{Book}          |                                        Get List of authors contributed in a Book                                        |                       BookId as Integer                        |  BookAuthorsList(BookId, [AuthorList])   |
|         /getAuthorBooks/{Author}         |                                       Get List of books an Author contributed in                                        |                      AuthorId as Integer                       |  BookAuthorsList(AuthorId, [BookList])   |
|              /addBookAuthor              |                   adds a record stating that the author with authorId Contributed to book with BookId                   |                         BookAuthorDto                          |       Message("Added Successfully)       |
|                                          |                                                                                                                         |                                                                |                                          |
|            /addBookBorrowing             | adds a record of a book being borrowed to a patron, uses Jwt attached token to get the librarian initiating the process | BookBorrowingDto(BookId, PatronId, LibrarianId, BorrowingDate) |       Message("Added Successfully)       |
| /addBookReturning/{Book}/patron/{Patron} | adds a record of a book being returned to a patron, uses Jwt attached token to get the librarian initiating the process |                        BookId, PatronId                        |     Message("Updated Successfully")      |
|                                          |                                                                                                                         |                                                                |                                          |
|               /getAllBooks               |                                  get all books, along with deleted and borrowed books                                   |                               /                                |              List of books               |
|             /getActiveBooks              |                                   get all books that are not deleted from the system                                    |                               /                                |              List of books               |
|            /getAvailableBooks            |                get books that are not deleted are not borrowed, books which are available to be borrowed                |                               /                                |              List of books               |
|              /getBook/{Id}               |                                            get book according to the BookId                                             |                       BookID as Integer                        |                   Book                   |
|        /getBookByIsbn/Isbn={Isbn}        |                                          get book according to the BookIsbn10                                           |                       BookIsbn as String                       |                   Book                   |
|                 /addBook                 |                                                 add book to the system                                                  |               Book(Title, Isbn, PublicationDate)               |       Message("Added Successfully)       |
|               /updateBook                |                                         update book preregistered in the system                                         |        Book(Id, Title, Isbn, PublicationDate, IsActive)        |     Message("Updated Successfully")      |
|             /deleteBook/{Id}             |                                               delete book from the system                                               |                       BookId as Integer                        |     Message("Updated Successfully")      |
|                                          |                                                                                                                         |                                                                |                                          |
|              /getAllPatrons              |                                       get all patrons, along with deleted patrons                                       |                               /                                |             List of Patrons              |
|            /getActivePatrons             |                                             get all patrons that are active                                             |                               /                                |             List of Patrons              |
|             /getPatron/{Id}              |                                            get patron according to PatronId                                             |                      PatronId as Integer                       |                  Patron                  |
|                /addPatron                |                                                add Patron to the system                                                 |        Patron(FirstName, LastName, Email, PhoneNumber)         |      Message("Added Successfully")       |
|              /updatePatron               |                                        update Patron preregistered in the system                                        |      Patron(Id, FirstName, LastName, Email, PhoneNumber)       |     Message("Updated Successfully")      |
|            /deletePatron/{Id}            |                                              delete Patron from the system                                              |                      PatronId as Integer                       |     Message("Updated Successfully")      |


## Project Database:

- Tables:
  > Book

  > Author

  > BookAuthor

  > Patron

  > Librarian (User)

  > BorrowingBook

  > ReturningBook

- Views:
  > BookBorrowingStateVw: BookBorrowing.Id, BookBorrowing.Book, BookBorrowing.Patron, BookBorrowing.Librarian, BookBorrowing.BorrowingBook, ReturningBook.RetuningDate.

- Stored Procedures:
  > Database stored procedures handles basic operations in the database like, Insert, Update, Delete, Get; alongside with basic join operation needed to connect tables with each other.

## Notes:

- SecurityConfig and JwtFilter enables Jwt Authorization producing a token containing UserName and an expiration date, Refresh Access token was not enabled due to the nature of the project.
for a library that operates on an 8-hour shift basics, expiration date is supposed to be 8 hours with no refresh token.

- Postman Collection constructed in order to test the project are attached to the Repo.
