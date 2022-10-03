package exceptions;

public class ObjectAlreadyAssignedException extends AppException {
	public ObjectAlreadyAssignedException(String objectName1, String objectId1, String objectName2, String objectId2) {
		super(ReturnError.OBJECT_ALREADY_ASSIGNED, ObjectAlreadyAssignedException.buildMessage(objectName1, objectId1, objectName2, objectId2));
	}

	public static String buildMessage(String objectName1, String objectId1, String objectName2, String objectId2) {
		return "The " + objectName1 + " " + objectId1 + " already assigned to " + objectName2 + " " + objectId2 + ".";
	}
}
