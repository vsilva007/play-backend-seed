package exceptions;

public class ForeignKeyException extends AppException {
	public ForeignKeyException(Integer code, String object) {
		super(code, buildMessage(object));
	}

	public static String buildMessage(String object) {
		return object.toUpperCase() + " is in use";
	}
}
