package daos;

import exceptions.ObjectNotFoundException;
import models.Lock;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LockDAO extends BaseDAO<Lock> {
	@Override
	public Class<?> getEntityClass() {
		return Lock.class;
	}

	public boolean deleteByName(String name) {
		try {
			Lock entity = this.findByName(name);
			if (entity != null) {
				this.server.delete(entity);
				return true;
			}
		} catch (ObjectNotFoundException ex) {
			// nothing to do
		}
		return false;
	}

	public Lock findByName(String name) throws ObjectNotFoundException {
		Lock model = (Lock) this.find.query().where().and().eq("name", name).findUnique();
		if (model != null) {
			return model;
		} else {
			throw new ObjectNotFoundException(getEntityClass().getSimpleName());
		}
	}

	public void deleteTenMinutesOlder() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -10);
		List<Lock> models = this.findAll();
		for (Lock model : models) {
			if (calendar.getTime().getTime() >= model.getCreated().getTime())
				this.server.delete(model);
		}
	}
}