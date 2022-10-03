package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import daos.PermissionDAO;
import exceptions.BadRequestException;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import models.BaseModel;
import models.Permission;

import java.time.Year;
import java.util.UUID;

public class PermissionService extends BaseService {
	public PermissionDAO dao;
	private SequenceService sequenceService;

	public PermissionService() {
		this.dao = new PermissionDAO();
		this.sequenceService = new SequenceService();
	}

	@Override
	PermissionDAO getDAO() {
		return this.dao;
	}

	public Permission create(JsonNode body) throws ObjectAlreadyExistException, BadRequestException {
		Permission permission = new Permission((ObjectNode) body);
		if (permission.id == null)
			permission.id = UUID.randomUUID();
		//Sets the friendly id
		try {
			permission.fid = Permission.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(Permission.class.getSimpleName()).value;
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
		try {
			this.dao.findById(permission.id);
			throw new ObjectAlreadyExistException(String.valueOf(permission.id));
		} catch (ObjectNotFoundException ex) {
			// nothing to do
		}
		this.save(permission);
		return permission;
	}

	public UUID delete(UUID id) throws ObjectNotFoundException {
		Permission permission = (Permission) this.findById(id);
		if (permission != null) {
			return this.deleteById(id);
		}
		throw new ObjectNotFoundException(Permission.class.getSimpleName(), String.valueOf(id));
	}
}
