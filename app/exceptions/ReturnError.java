package exceptions;

public class ReturnError {
	public static final Integer UNAUTHORIZED = 401;
	public static final Integer INTERNAL_SERVER_ERROR = 500;
	public static final Integer APP_ERROR = 600;
	public static final Integer APP_EXCEPTION = 601;
	public static final Integer ENTITY_NOT_FOUND = 602;
	public static final Integer MISSING_REQUIRED_FILES = 610;
	public static final Integer VALIDATION_ERROR = 620;
	public static final Integer WRONG_LOGIN = 640;
	public static final Integer OBJECT_ALREADY_EXIST = 650;
	public static final Integer CIF_ALREADY_EXIST = 651;
	public static final Integer PLANNING_NOT_DELETABLE = 652;
	public static final Integer PLANNING_NOT_EDITABLE = 653;
	public static final Integer CLIENT_CODE_ALREADY_EXIST = 654;
	public static final Integer PLANNING_NOT_ASSIGNED = 655;
	public static final Integer EXTENSION_NOT_ACCEPTED = 670;
	public static final Integer FILE_NOT_EXIST = 671;
	public static final Integer NOT_POSSIBLE_CREATE_DIRECTORY = 672;
	public static final Integer NOT_POSSIBLE_SAVE_FILE = 673;
	public static final Integer OBJECT_ALREADY_ASSIGNED = 680;
	public static final Integer FOREIGN_KEY_VIOLATION_UPD_CLI = 690;
	public static final Integer FOREIGN_KEY_VIOLATION_DEL_LOC = 691;

	private ReturnError() {
	}
}
