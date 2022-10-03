package daos;

import exceptions.ObjectNotFoundException;
import io.ebean.*;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO extends BaseDAO<User> {
	@Override
	public Class<?> getEntityClass() {
		return User.class;
	}

	public User findByUserAndPassword(String login, String password) throws ObjectNotFoundException {
		User model = (User) this.find.query().where().and().eq("login", login).eq("password", password).and().eq("active", Boolean.TRUE).findUnique();
		if (model != null) {
			return model;
		} else {
			throw new ObjectNotFoundException(getEntityClass().getSimpleName(), login);
		}
	}

	public void deleteBasesForUserId(UUID id) {
		SqlUpdate sqlUpdate = Ebean.getServer("default").createSqlUpdate("DELETE FROM pdb_user_pdb_bases WHERE pdb_user_id = '" + id + "';");
		sqlUpdate.execute();
	}

	public List<User> findAllWithBaseId(String baseId) {
		String sql = "SELECT id, fid, name, surname, login, role_id, created, updated, last_login, login_count FROM pdb_user WHERE id IN (SELECT pdb_user_id FROM pdb_user_pdb_bases WHERE pdb_bases_id = '" + baseId + "');";
		RawSql rawSql = RawSqlBuilder.parse(sql).create();
		Query<User> query = Ebean.getServer("default").find(User.class);
		query.setRawSql(rawSql);
		List<User> modelList = new ArrayList<User>();
		modelList = query.findList();
		return modelList;
	}
}
