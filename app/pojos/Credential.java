package pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credential {
	private String login;
	private String password;

	public Credential(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	// @formatter:off
	public String toString() {
		return "Credential{" +
 				"login='" + login + '\'' +
				", password='" + password + '\'' + '}';
	}
}
