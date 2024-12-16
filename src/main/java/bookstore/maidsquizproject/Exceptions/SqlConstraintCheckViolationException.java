package bookstore.maidsquizproject.Exceptions;

public class SqlConstraintCheckViolationException extends Exception
{
	public SqlConstraintCheckViolationException()
	{
		super("Inconsistent Data provided");
	}

	public SqlConstraintCheckViolationException(String message)
	{
		super(message);
	}
}
