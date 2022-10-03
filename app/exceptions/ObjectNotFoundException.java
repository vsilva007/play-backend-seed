package exceptions;

public class ObjectNotFoundException extends AppException {
	public ObjectNotFoundException(String objectName) {
		super(ObjectNotFoundException.buildMessage(objectName));
	}

	public ObjectNotFoundException(String objectName, String id) {
		super(ReturnError.ENTITY_NOT_FOUND, ObjectNotFoundException.buildMessage(objectName, id));
	}

	public static String buildMessage(String objectName) {
		return "Object " + objectName + " does not exist";
	}

	public static String buildMessage(String objectName, String id) {
		return "Object " + objectName + " (ID: " + id + ") does not exist";
	}
}
