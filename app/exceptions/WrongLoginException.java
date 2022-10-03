package exceptions;

public class WrongLoginException extends AppException {
	public WrongLoginException() {
		super(ReturnError.WRONG_LOGIN, WrongLoginException.buildMessage());
	}

	public static String buildMessage() {
		return "Email or password is wrong.";
	}
}
