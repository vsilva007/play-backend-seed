package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.RoleService;

import javax.inject.Inject;
import java.util.UUID;

public class RoleController extends BaseController {
	private RoleService appService = null;

	@Inject
	public RoleController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	RoleService getService() {
		return new RoleService();
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

	public Result delete(UUID id) {
		try {
			return this.returnOk(Json.toJson(appService.delete(id)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}
}
