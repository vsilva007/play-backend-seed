package services;

import daos.LockDAO;
import exceptions.ObjectNotFoundException;
import models.BaseModel;
import models.Lock;

public class LockService extends BaseService {
	public LockDAO lockDAO;

	public LockService() {
		this.lockDAO = new LockDAO();
	}

	@Override
	LockDAO getDAO() {
		return this.lockDAO;
	}

	public Lock findByName(String name) {
		Lock lock = null;
		try {
			lock = (Lock) this.getDAO().findByName(name);
		} catch (ObjectNotFoundException e) {
			return null;
		}
		return lock;
	}

	public Lock create(String name) {
		Lock lock = new Lock(name);
		this.save(lock);
		return lock;
	}

	public boolean delete(String name) {
		return this.getDAO().deleteByName(name);
	}

	public void deleteTenMinutesOlder() {
		this.getDAO().deleteTenMinutesOlder();
	}
}
