package pojos;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import services.helpers.JsonHelper;

import java.io.IOException;
import java.util.Arrays;

@Getter
@Setter
public class DateFilter {
	private String[] data;

	public DateFilter(String[] data) {
		this.data = data;
	}

	public DateFilter(ObjectNode json) throws IOException {
		JsonHelper.getMapper().readerForUpdating(this).readValue(json);
	}

	@Override
	public String toString() {
		return "DateFilter{" + ", data=" + Arrays.toString(data) + '}';
	}
}
