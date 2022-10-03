package services.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class JsonHelper {
	static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectNode errorToJson(int code, String msg) {
		ObjectNode result = Json.newObject();
		result.put("code", code);
		result.put("message", msg);
		return result;
	}

	public static ObjectNode errorToJson(int code, Throwable t) {
		return errorToJson(code, t.getMessage());
	}

	public static ObjectMapper getMapper() {
		return mapper;
	}
}
