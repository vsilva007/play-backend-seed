package models;

import io.ebean.Finder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "pdb_lock")
public class Lock extends BaseModel {
	public static final String LOCK_DATA_PROCESS = "DATA_PROCESS";
	public static final String LOCK_AX_FILE = "AX_FILE";
	public static final String LOCK_HEPTAN_FILE = "HEPTAN_FILE";
	public static final String LOCK_HEPTAN_DATALOAD = "HEPTAN_DATALOAD";
	public static final String LOCK_VEOSAT_FILE = "VEOSAT_FILE";
	public static final String LOCK_VEOSAT_DATALOAD = "VEOSAT_DATALOAD";
	public static Finder<Long, Lock> find = new Finder<Long, Lock>(Lock.class, "default");
	public String name;

	public Lock(String name) {
		this.name = name;
	}

	@Override
	// @formatter:off
	public String toString() {
		return "Lock{" + "name='" + name + '\'' + ", id=" + id + '}';
	}
}



