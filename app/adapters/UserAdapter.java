package adapters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import models.Role;
import models.User;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("User")
public class UserAdapter {
	private UUID id;
	private String fid;
	private String name;
	private String surname;
	private String login;
	private Role role;

	public UserAdapter(User user) {
		this.id = user.getId();
		this.fid = user.getFid();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.login = user.getLogin();
		this.role = user.getRole();
	}

	@Override
	public String toString() {
		return "UserAdapter{" + "id=" + id + ", fid='" + fid + '\'' + ", name='" + name + '\'' + ", surname='" +
				surname + '\'' + ", login='" + login + '\'' + ", role=" + role + '}';
	}
}
