package exceptions;

public class ExtensionNotAcceptedException extends AppException {
	public ExtensionNotAcceptedException() {
		super(ReturnError.EXTENSION_NOT_ACCEPTED, ExtensionNotAcceptedException.buildMessage());
	}

	public ExtensionNotAcceptedException(String extensionsAccepted) {
		super(ReturnError.EXTENSION_NOT_ACCEPTED, ExtensionNotAcceptedException.buildMessage(extensionsAccepted));
	}

	private static String buildMessage() {
		return "Extension not accepted.";
	}

	private static String buildMessage(String extensionAccepted) {
		return "Extension not accepted. Only accept the  extensions (" + extensionAccepted + ").";
	}
}
