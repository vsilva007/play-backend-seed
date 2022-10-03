package exceptions;

public class NotPossibleCreateDirectoryException extends AppException {
	public NotPossibleCreateDirectoryException() {
		super(ReturnError.NOT_POSSIBLE_CREATE_DIRECTORY, NotPossibleCreateDirectoryException.buildMessage());
	}

	private static String buildMessage() {
		return "It isn't possible to create the directory.";
	}
}
