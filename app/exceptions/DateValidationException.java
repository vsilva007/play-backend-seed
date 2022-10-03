package exceptions;

public class DateValidationException extends AppException {
	public DateValidationException(String id) {
		super(ReturnError.VALIDATION_ERROR, DateValidationException.buildMessage(id));
	}

	public static String buildMessage(String id) {
		return "Date validation error for entity " + id;
	}
}
