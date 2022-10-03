package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.BadRequestException;
import io.ebean.Finder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "pdb_user")
public class User extends BaseModel {
	public static final String SEQ_PREFIX = "USR";
	public static Finder<Long, User> find = new Finder<Long, User>(User.class);
	public String fid;
	public String name;
	public String surname;
	public String login;
	public String password;
	@ManyToOne
	public Role role;
	public long lastLogin;
	public long loginCount;
	public Boolean active;

	public User(UUID id, String name, String surname, long lastLogin, long loginCount, Boolean active) {
		super.id = id;
		this.name = name;
		this.surname = surname;
		this.lastLogin = lastLogin;
		this.loginCount = loginCount;
		this.active = active;
	}

	public User(String name, String surname, String login, String password, Role role, long lastLogin, long loginCount, Boolean active) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
		this.lastLogin = lastLogin;
		this.loginCount = loginCount;
		this.active = active;
	}

	public User(ObjectNode json) throws BadRequestException {
		this.createFromJson(json);
	}

	public static List<User> findAll() {
		return User.find.all();
	}

	@Override
	// @formatter:off
	public String toString() {
		return "User{" +
 				"name='" + name + '\'' +
 				", surname='" + surname + '\'' +
 				", login='" + login + '\'' +
 				", password='" + password + '\'' +
 				", role=" + role +
 				", lastLogin=" + lastLogin +
 				", loginCount=" + loginCount +
 				", active=" + active +
				", fid=" + fid +
				", id=" + id + '}';
	}

	public void byUser(User user) {
		this.name = user.getName();
		this.surname = user.getSurname();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.role = user.getRole();
		this.active = user.getActive();
	}
}



