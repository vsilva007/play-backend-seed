package exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class AppException extends IOException {
	private Integer apiError = ReturnError.APP_ERROR;

	public AppException() {
		super();
	}

	public AppException(Integer apiError, String message) {
		super(message);
		this.apiError = apiError;
	}

	public AppException(String msg) {
		super(msg);
		this.apiError = ReturnError.APP_EXCEPTION;
	}

	public AppException(Exception e) {
		super(e);
	}

	public AppException(String msg, Exception e) {
		super(msg, e);
	}

	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode result = mapper.createObjectNode();
		result.put("code", this.getApiError());
		result.put("message", this.getMessage());
		return result;
	}
}
