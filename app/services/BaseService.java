package services;

import daos.BaseDAO;
import exceptions.ObjectNotFoundException;
import io.ebean.Ebean;
import models.BaseModel;

import java.util.List;
import java.util.UUID;

public abstract class BaseService<D extends BaseDAO<E>, E extends BaseModel> {
	abstract D getDAO();

	public List<E> findAll() {
		return this.getDAO().findAll();
	}

	public E findById(UUID id) throws ObjectNotFoundException {
		return this.getDAO().findById(id);
	}

	public void save(E entity) {
		Ebean.getServer("default").save(entity);
	}

	public E update(E entity) {
		return this.getDAO().update(entity);
	}

	public UUID deleteById(UUID id) throws ObjectNotFoundException {
		return this.getDAO().deleteById(id);
	}
}
