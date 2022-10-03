package daos;

import exceptions.ObjectNotFoundException;
import io.ebean.Model;
import models.Sequence;

public class SequenceDAO extends BaseDAO {
	@Override
	public Class<?> getEntityClass() {
		return Sequence.class;
	}

	public Object findByName(String name) throws ObjectNotFoundException {
		Model model = (Model) this.find.query().where().and().eq("name", name).findUnique();
		if (model != null) {
			return model;
		} else {
			throw new ObjectNotFoundException(name);
		}
	}
}
