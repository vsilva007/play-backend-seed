package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BaseService;

import java.util.UUID;

public abstract class BaseController extends Controller {
	abstract BaseService getService();

	public Result returnOk(JsonNode json) {
		if (json == null) {
			return status(OK);
		} else {
			return status(OK, json);
		}
	}

	public Result returnError(int code, Exception e) {
		if (e instanceof AppException)
			return status(code, ((AppException) e).toJson());
		else
			return internalServerError();
	}

	public Result find(UUID id) {
		try {
			return this.returnOk(Json.toJson(getService().findById(id)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result findAll() {
		try {
			return this.returnOk(Json.toJson(getService().findAll()));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}
}