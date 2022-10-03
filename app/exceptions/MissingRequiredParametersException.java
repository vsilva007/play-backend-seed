package exceptions;

import com.google.common.base.Joiner;

import java.util.List;

public class MissingRequiredParametersException extends AppException {
	public MissingRequiredParametersException(List<String> fields) {
		super(ReturnError.MISSING_REQUIRED_FILES, MissingRequiredParametersException.buildMessage(fields));
	}

	public static String buildMessage(List<String> fields) {
		return "The parameters (" + Joiner.on(", ").skipNulls().join(fields) + ") are mandatory.";
	}
}
