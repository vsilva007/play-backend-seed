package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.BadRequestException;
import io.ebean.Finder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "pdb_role")
public class Role extends BaseModel {
	public static final String SEQ_PREFIX = "ROL";
	public static Finder<Long, Role> find = new Finder<Long, Role>(Role.class);
	public String fid;
	public String type;
	@OneToMany(targetEntity = Permission.class, cascade = CascadeType.ALL)
	public Set<Permission> permissions;

	public Role(String type, Set<Permission> permissions) {
		this.type = type;
		this.permissions = permissions;
	}

	public Role(ObjectNode json) throws BadRequestException {
		this.createFromJson(json);
	}

	public static List<Role> findAll() {
		return Role.find.all();
	}

	@Override
	// @formatter:off
	public String toString() {
		return "Role{" +
 				"type='" + type + '\'' +
 				", permissions=" + permissions +
 				", fid=" + fid +
 				", id=" + id + '}';
	}

	public void byRole(Role role) {
		this.type = role.getType();
		this.permissions = role.getPermissions();
	}
}



