package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import daos.RoleDAO;
import exceptions.BadRequestException;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import models.Permission;
import models.Role;

import java.time.Year;
import java.util.UUID;

public class RoleService extends BaseService<RoleDAO, Role> {
	public RoleDAO dao;
	private PermissionService permissionService;
	private SequenceService sequenceService;

	public RoleService() {
		this.dao = new RoleDAO();
		this.permissionService = new PermissionService();
		this.sequenceService = new SequenceService();
	}

	@Override
	RoleDAO getDAO() {
		return this.dao;
	}

	public Role create(JsonNode body) throws ObjectAlreadyExistException, BadRequestException {
		Role role = new Role((ObjectNode) body);
		if (role.id == null)
			role.id = UUID.randomUUID();
		//Sets the friendly id
		try {
			role.fid = Role.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(Role.class.getSimpleName()).value;
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
		try {
			this.dao.findById(role.id);
			throw new ObjectAlreadyExistException(String.valueOf(role.id));
		} catch (ObjectNotFoundException ex) {
			// nothing to do
		}
		try {
			this.dao.findByType(role.type);
			throw new ObjectAlreadyExistException(String.valueOf(role.type));
		} catch (ObjectNotFoundException ex) {
			// nothing to do
		}
		setUpPermissions(role);
		this.save(role);
		return role;
	}

	public Role update(JsonNode body) throws ObjectNotFoundException, BadRequestException {
		Role role = new Role((ObjectNode) body);
		if (role.id == null)
			throw new ObjectNotFoundException(String.valueOf(role.id));
		Role current = (Role) this.dao.findById(role.id);
		current.byRole(role);
		setUpPermissions(role);
		return (Role) this.update(current);
	}

	public UUID delete(UUID id) throws ObjectNotFoundException {
		Role role = (Role) this.findById(id);
		if (role != null) {
			return this.deleteById(id);
		}
		throw new ObjectNotFoundException(Role.class.getSimpleName(), String.valueOf(id));
	}

	private Role setUpPermissions(Role role) throws BadRequestException {
		Permission[] permissions = role.permissions.toArray(new Permission[role.permissions.size()]);
		role.permissions.clear();
		for (Permission permission : permissions) {
			if (permission.fid == null || permission.fid.equals("")) {
				//Sets the friendly id
				try {
					permission.fid = Permission.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(Permission.class.getSimpleName()).value;
				} catch (Exception e) {
					throw new BadRequestException(e.getMessage());
				}
			}
			role.permissions.add(permission);
		}
		return role;
	}
}
