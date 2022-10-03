package exceptions;

public class BadRequestException extends AppException {
	public BadRequestException() {
		super();
	}

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(String msg, Exception e) {
		super(msg, e);
	}
}
