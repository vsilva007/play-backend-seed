package exceptions;

public class FileNotExistException extends AppException {
	public FileNotExistException() {
		super(ReturnError.FILE_NOT_EXIST, FileNotExistException.buildMessage());
	}

	public FileNotExistException(String fileName) {
		super(ReturnError.FILE_NOT_EXIST, FileNotExistException.buildMessage(fileName));
	}

	private static String buildMessage() {
		return "The file does not exist.";
	}

	private static String buildMessage(String fileName) {
		return "The file (" + fileName + ") does not exist.";
	}
}
