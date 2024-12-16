package bookstore.maidsquizproject.Models;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ApiErrorResponse<T> extends ResponseEntity<T>
{
	public ApiErrorResponse(HttpStatusCode status)
	{
		super(status);
	}

	public ApiErrorResponse(T body, HttpStatusCode status)
	{
		super(body, status);
	}
}
