package exceptions;

public class NotPossibleSaveFileException extends AppException {
	public NotPossibleSaveFileException() {
		super(ReturnError.NOT_POSSIBLE_SAVE_FILE, NotPossibleSaveFileException.buildMessage());
	}

	private static String buildMessage() {
		return "It isn't possible to save the file.";
	}
}
