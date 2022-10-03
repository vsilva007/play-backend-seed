package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.BadRequestException;
import io.ebean.Finder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pdb_permission")
public class Permission extends BaseModel {
	public static final String SEQ_PREFIX = "PERM";
	public static Finder<Long, Permission> find = new Finder<Long, Permission>(Permission.class);
	public String fid;
	public byte code;
	public String name;

	public Permission(byte code, String name) {
		this.code = code;
		this.name = name;
	}

	public Permission(ObjectNode json) throws BadRequestException {
		this.createFromJson(json);
	}

	public static List<Permission> findAll() {
		return Permission.find.all();
	}

	@Override
	// @formatter:off
	public String toString() {
		return "Permission{" +
 				"code=" + code +
 				", name='" + name + '\'' +
 				", fid=" + fid +
 				", id=" + id + '}';
	}
}



