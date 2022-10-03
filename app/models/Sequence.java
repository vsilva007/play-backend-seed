package models;

import io.ebean.Finder;
import io.ebean.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "pdb_sequence")
public class Sequence extends Model {
	public static Finder<Long, Sequence> find = new Finder<Long, Sequence>(Sequence.class);
	@Id
	public String name;
	public Long value;

	public Sequence(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	@Override
	// @formatter:off
	public String toString() {
		return "Sequence{" +
 				"name='" + name + '\'' +
 				", value=" + value + '}';
	}
}



