package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.util.UUID;

public class UserController extends BaseController {
	private UserService appService = null;

	@Inject
	public UserController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	UserService getService() {
		return new UserService();
	}

	public Result findAllForBase(UUID baseId) {
		try {
			return this.returnOk(Json.toJson(getService().findWithBaseId(baseId)));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result create() {
		try {
			JsonNode body = request().body().asJson();
			return this.returnOk(Json.toJson(appService.create(body)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result update() {
		try {
			JsonNode body = request().body().asJson();
			return this.returnOk(Json.toJson(appService.update(body)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result login() {
		try {
			JsonNode body = request().body().asJson();
			return this.returnOk(Json.toJson(appService.appLogin(body)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result delete(UUID id) {
		try {
			return this.returnOk(Json.toJson(appService.delete(id)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result findDrivers() {
		try {
			return this.returnOk(Json.toJson(getService().findDrivers()));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result refresh(UUID id) {
		try {
			return this.returnOk(Json.toJson(getService().refreshUserData(id)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}
}
