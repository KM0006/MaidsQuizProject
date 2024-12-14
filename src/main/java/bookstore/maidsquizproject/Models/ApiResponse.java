package bookstore.maidsquizproject.Models;

public class ApiResponse<T>
{
	public T Data;
	public int Status;
	public String Message;

	public ApiResponse(T data, int status, String message)
	{
		Data = data;
		Status = status;
		Message = message;
	}

	public ApiResponse(T data)
	{
		Data = data;
		Status = 200;
		Message = "Success";
	}

}
