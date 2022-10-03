package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import exceptions.AppException;
import models.User;
import org.apache.commons.collections4.CollectionUtils;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import security.JwtValidator;
import security.VerifiedJwt;
import services.UserService;
import utils.EncryptionUtils;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class LoginController extends BaseController {
	private UserService appService = null;
	@Inject
	private Config config;
	@Inject
	private JwtValidator tokenValidator;

	@Inject
	public LoginController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	UserService getService() {
		return new UserService();
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result login() throws UnsupportedEncodingException {
		try {
			JsonNode body = request().body().asJson();
			User user = appService.login(body);
			if (null != user && null != user.getRole() && CollectionUtils.isNotEmpty(user.getRole().getPermissions())) {
				ObjectNode result = Json.newObject();
				List<Integer> permissions = new ArrayList<>();
				user.getRole().getPermissions().forEach(permission -> permissions.add((int) permission.getCode()));
				result.put("access_token", getSignedToken(user.getId(), permissions.toArray(new Integer[0]), 10L));
				result.put("refresh_token", getSignedToken(user.getId(), permissions.toArray(new Integer[0]), 24L * 60)); // 24H validity
				return ok(result);
			}
			return forbidden();
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return forbidden();
		}
	}

	public Result refresh() throws UnsupportedEncodingException {
		try {
			String refreshToken = request().header("refresh_token").orElse(null);
			F.Either<JwtValidator.Error, VerifiedJwt> res = tokenValidator.verify(refreshToken);
			if (res.left.isPresent()) {
				return forbidden();
			}
			User user = appService.findById(UUID.fromString(res.right.get().getUserId()));
			if (null != user) {
				ObjectNode result = Json.newObject();
				List<Integer> permissions = new ArrayList<>();
				user.getRole().getPermissions().forEach(permission -> permissions.add((int) permission.getCode()));
				result.put("access_token", getSignedToken(user.getId(), permissions.toArray(new Integer[0]), 10L));
				return ok(result);
			}
			return forbidden();
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return forbidden();
		}
	}

	public Result encryptDbPasswords() {
		try {
			List<User> users = this.appService.findAll();
			users.forEach(user -> {
				if (!EncryptionUtils.isDESedeEncrypted(user.getPassword())) {
					user.setPassword(EncryptionUtils.encryptDESede(user.getPassword()));
					this.appService.update(user);
				}
			});
		} catch (Exception e) {
			return returnError(412, e);
		}
		return ok("All Done");
	}

	public Result generateIntegrationToken() throws UnsupportedEncodingException {
		try {
			JsonNode body = request().body().asJson();
			//			User user = appService.login(body);
			String userId = body.get("id").asText();
			if (null == userId)
				return forbidden();
			if (null == body.get("duration"))
				return returnError(412, new AppException("No duration specified"));
			User user = appService.findById(UUID.fromString(userId));
			if (null != user && null != user.getRole() && CollectionUtils.isNotEmpty(user.getRole().getPermissions())) {
				ObjectNode result = Json.newObject();
				List<Integer> permissions = new ArrayList<>();
				user.getRole().getPermissions().forEach(permission -> permissions.add((int) permission.getCode()));
				result.put("token", getSignedToken(user.getId(), permissions.toArray(new Integer[0]), body.get("duration").asLong()));
				return ok(result);
			}
			return forbidden();
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return forbidden();
		}
	}

	// @formatter:off
	private String getSignedToken(UUID userId, Integer[] perms, Long minutes) throws UnsupportedEncodingException {
		String secret = config.getString("play.http.secret.key");
		Algorithm algorithm = Algorithm.HMAC256(secret);
		Date expires = Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(minutes).toInstant());

		return JWT.create()
				.withIssuer("smart-tpv")
				.withClaim("user_id", userId.toString())
				.withArrayClaim("permissions", perms)
				.withExpiresAt(expires)
				.sign(algorithm);
	}


}
