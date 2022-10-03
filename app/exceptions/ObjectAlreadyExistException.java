package exceptions;

public class ObjectAlreadyExistException extends AppException {
	public ObjectAlreadyExistException(String id) {
		super(ReturnError.OBJECT_ALREADY_EXIST, ObjectAlreadyExistException.buildMessage(id));
	}

	public static String buildMessage(String id) {
		return "This object ('" + id + "') already exists.";
	}
}
