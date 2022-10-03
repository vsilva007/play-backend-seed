package daos;

import models.Permission;

public class PermissionDAO extends BaseDAO<Permission> {
	@Override
	public Class<?> getEntityClass() {
		return Permission.class;
	}
}
