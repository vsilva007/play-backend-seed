package daos;

import exceptions.ObjectNotFoundException;
import models.BaseModel;
import models.Role;

public class RoleDAO extends BaseDAO<Role> {
	@Override
	public Class<?> getEntityClass() {
		return Role.class;
	}

	public BaseModel findByType(String type) throws ObjectNotFoundException {
		BaseModel model = (BaseModel) this.find.query().where().and().eq("type", type).findUnique();
		if (model != null) {
			return model;
		} else {
			throw new ObjectNotFoundException(getEntityClass().getSimpleName(), type);
		}
	}
}
