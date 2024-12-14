package bookstore.maidsquizproject.DataTransferObjects;

public class PatronDto
{
	public String FirstName;

	public String LastName;

	public String Email;

	public String PhoneNumber;

	public boolean IsActive;

	public PatronDto() {}

	public PatronDto(String firstName, String lastName, String email, String phoneNumber, boolean isActive)
	{
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		IsActive = isActive;
	}
}
