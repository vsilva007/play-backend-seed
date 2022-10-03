package exceptions;

public class UnauthorizedException extends AppException {
	public UnauthorizedException(String email) {
		super(ReturnError.UNAUTHORIZED, UnauthorizedException.buildMessage(email));
	}

	public static String buildMessage(String email) {
		return "Unauthorized " + email;
	}
}
