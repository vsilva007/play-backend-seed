package services;

import daos.SequenceDAO;
import exceptions.MissingRequiredParametersException;
import exceptions.ObjectNotFoundException;
import io.ebean.Ebean;
import models.BaseModel;
import models.Sequence;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceService extends BaseService {
	public SequenceDAO dao;

	public SequenceService() {
		this.dao = new SequenceDAO();
	}

	@Override
	SequenceDAO getDAO() {
		return this.dao;
	}

	public Sequence create(String name) {
		Sequence sequence = new Sequence(name, 1L);
		Ebean.getServer("default").save(sequence);
		return sequence;
	}

	public boolean exist(String name) throws MissingRequiredParametersException, ObjectNotFoundException {
		if (name == null || name.equals(""))
			throw new MissingRequiredParametersException(new ArrayList<>(Arrays.asList(name)));
		Sequence current;
		try {
			current = (Sequence) this.dao.findByName(name);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public Sequence nextValue(String name) throws MissingRequiredParametersException, ObjectNotFoundException {
		if (!exist(name)) {
			Sequence seq = this.create(name);
			return seq;
		}
		Sequence seq = (Sequence) this.dao.findByName(name);
		seq.setValue(seq.getValue() + 1);
		Ebean.getServer("default").update(seq);
		return seq;
	}
}
