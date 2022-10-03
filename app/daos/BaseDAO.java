package daos;

import exceptions.ObjectNotFoundException;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Finder;
import models.BaseModel;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
@SuppressWarnings("unchecked")
abstract public class BaseDAO<E extends BaseModel> {
	protected final EbeanServer server = Ebean.getServer("default");
	protected Finder find = null;

	public BaseDAO() {
		this.find = new Finder<>(this.getEntityClass(), "default");
	}

	abstract public Class<?> getEntityClass();

	public E save(E entity) {
		this.server.insert(entity);
		return entity;
	}

	public E update(E entity) {
		this.server.update(entity);
		return entity;
	}

	public E findById(UUID id) throws ObjectNotFoundException {
		E model = (E) this.find.byId(id);
		if (model != null) {
			return model;
		} else {
			throw new ObjectNotFoundException(getEntityClass().getSimpleName(), String.valueOf(id));
		}
	}

	public List<E> findAll() {
		List<E> all = this.find.all();
		all.sort(Comparator.comparing(E::getCreated));
		return all;
	}

	public UUID deleteById(UUID id) throws ObjectNotFoundException {
		E entity = (E) this.find.byId(id);
		if (entity != null) {
			server.delete(entity);
			return id;
		} else {
			throw new ObjectNotFoundException(this.getEntityClass().getSimpleName(), String.valueOf(id));
		}
	}
}
