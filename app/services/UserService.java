package services;

import adapters.UserAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import daos.UserDAO;
import exceptions.BadRequestException;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import models.Permission;
import models.User;
import play.Logger;
import utils.EncryptionUtils;

import java.time.Instant;
import java.time.Year;
import java.util.*;

public class UserService extends BaseService<UserDAO, User> {
	public UserDAO dao;
	private RoleService roleService;
	private SequenceService sequenceService;

	public UserService() {
		this.dao = new UserDAO();
		this.roleService = new RoleService();
		this.sequenceService = new SequenceService();
	}

	public UserService(boolean onlyDao) {
		this.dao = new UserDAO();
		this.roleService = new RoleService();
		this.sequenceService = new SequenceService();
	}

	@Override
	UserDAO getDAO() {
		return this.dao;
	}

	public List<User> findWithBaseId(UUID baseId) {
		List<User> userList = this.getDAO().findAllWithBaseId(String.valueOf(baseId));
		userList.sort(Comparator.comparing(User::getCreated));
		return userList;
	}

	public User create(JsonNode body) throws ObjectAlreadyExistException, BadRequestException {
		User user = new User((ObjectNode) body);
		if (user.id == null)
			user.id = UUID.randomUUID();
		// Sets the friendly id
		try {
			user.fid = User.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(User.class.getSimpleName()).value;
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
		// Check if role exists
		try {
			roleService.findById(user.role.id);
		} catch (ObjectNotFoundException ex) {
			Logger.debug(ex.getMessage(), ex);
			throw new BadRequestException(ex.getMessage());
		}
		// Check if user already exists
		try {
			this.dao.findById(user.id);
			throw new ObjectAlreadyExistException(String.valueOf(user.id));
		} catch (ObjectNotFoundException ex) {
			// nothing to do
		}
		// encrypt password
		user.setPassword(EncryptionUtils.encryptDESede(user.getPassword()));
		user.setActive(user.active == null ? Boolean.TRUE : user.active);
		// Create the user
		this.save(user);
		return user;
	}

	public User update(JsonNode body) throws ObjectNotFoundException, BadRequestException {
		User user = new User((ObjectNode) body);
		if (user.id == null)
			throw new ObjectNotFoundException(String.valueOf(user.id));
		User current = this.dao.findById(user.id);
		current.byUser(user);
		String password = EncryptionUtils.isDESedeEncrypted(current.getPassword()) ? current.getPassword() : EncryptionUtils.encryptDESede(current.getPassword());
		current.setPassword(password);
		return this.update(current);
	}

	public UUID delete(UUID id) throws ObjectNotFoundException {
		User user = this.findById(id);
		if (user != null) {
			this.getDAO().deleteBasesForUserId(id);
			return this.deleteById(id);
		}
		throw new ObjectNotFoundException(User.class.getSimpleName(), String.valueOf(id));
	}

	public UserAdapter appLogin(JsonNode body) throws ObjectNotFoundException {
		User user = this.login(body);
		return new UserAdapter(user);
	}

	public User login(JsonNode body) throws ObjectNotFoundException {
		User user = this.dao.findByUserAndPassword(body.get("login").asText(), Objects.requireNonNull(EncryptionUtils.encryptDESede(body.get("password").asText())));
		user.setLastLogin(Instant.now().toEpochMilli());
		user.setLoginCount(user.getLoginCount() + 1);
		this.update(user);
		return user;
	}

	public User[] findDrivers() {
		List<User> res = new ArrayList<>();
		List<User> users = this.findAll();
		for (User user : users) {
			for (Permission permission : user.getRole().getPermissions()) {
				if (permission.getName().equals("driver") && user.active) {
					res.add(user);
					break;
				}
			}
		}
		return res.toArray(new User[res.size()]);
	}

	public UserAdapter refreshUserData(UUID id) throws ObjectNotFoundException {
		User user = this.dao.findById(id);
		return new UserAdapter(user);
	}
}
