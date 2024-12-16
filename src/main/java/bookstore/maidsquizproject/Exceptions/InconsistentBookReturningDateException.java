package bookstore.maidsquizproject.Exceptions;

public class InconsistentBookReturningDateException extends Exception
{
	public InconsistentBookReturningDateException()
	{
		super("Inconsistent Book Returning Date, Returning Date must be Later than Borrowing Date");
	}

	public InconsistentBookReturningDateException(String message)
	{
		super(message);
	}
}
