package bookstore.maidsquizproject.Exceptions;

import bookstore.maidsquizproject.Models.ApiErrorResponse;
import bookstore.maidsquizproject.Models.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ApiErrorResponse<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException)
	{
		return new ApiErrorResponse<>
		(
			new ApiResponse<>
			(
					"Entity not found",
					HttpStatus.NOT_FOUND.value(),
					"Not Found"
			),
			HttpStatus.NOT_FOUND
		);
	}

	@ExceptionHandler(BadRequestException.class)
	public ApiErrorResponse<ApiResponse<String>> handleBadRequestException(BadRequestException badRequestException)
	{
		return new ApiErrorResponse<>
		(
			new ApiResponse<>
			(
				"Bad Request",
				HttpStatus.BAD_REQUEST.value(),
				"Request constructed improperly."
			),
			HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiErrorResponse<ApiResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException)
	{
		return new ApiErrorResponse<>
		(
			new ApiResponse<>
			(
				"Wrong Input data format",
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Request constructed improperly."
			),
			HttpStatus.UNPROCESSABLE_ENTITY
		);
	}


	@ExceptionHandler(InconsistentBookReturningDateException.class)
	public ApiErrorResponse<ApiResponse<String>> handleInconsistentBookReturningDateException(InconsistentBookReturningDateException inconsistentBookReturningDateException)
	{
		return new ApiErrorResponse<>
		(
			new ApiResponse<>
				(
					inconsistentBookReturningDateException.getMessage(),
					HttpStatus.CONFLICT.value(),
					"Inconsistent Returning Date, Returning Date must be after Borrowing Date "
				),
			HttpStatus.CONFLICT
		);
	}

}

