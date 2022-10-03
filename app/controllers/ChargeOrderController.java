package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.ChargeOrderService;

import javax.inject.Inject;
import java.util.UUID;

public class ChargeOrderController extends BaseController {
	private ChargeOrderService appService = null;

	@Inject
	public ChargeOrderController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	ChargeOrderService getService() {
		return new ChargeOrderService();
	}
	//    public Result findNoHistorified() {
	//        try {
	//            return this.returnOk(Json.toJson(getService().findNoHistorified()));
	//        } catch (Exception e) {
	//            Logger.debug(e.getMessage(), e);
	//            return this.returnError(Http.Status.NOT_FOUND, e);
	//        }
	//    }

	@BodyParser.Of(BodyParser.Json.class)
	public Result findAllForUser() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(getService().findAll(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result findHistorifiedForUser() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(getService().findHistorified(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result create() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.create(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result update() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.update(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result delete(UUID id) {
		try {
//			return this.returnOk(Json.toJson(appService.delete(id)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result findAssignable(UUID userId) {
		try {
//			return this.returnOk(Json.toJson(getService().findAssignable(userId)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result updateNotes(UUID id, String value) {
		try {
//			return this.returnOk(Json.toJson(getService().updateNotes(id, value)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result updateModality(UUID id, String value) {
		try {
//			return this.returnOk(Json.toJson(getService().updateModality(id, value)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result updateExpiration(UUID id, Long value) {
		try {
//			return this.returnOk(Json.toJson(getService().updateExpiration(id, value)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result updateOptimal(UUID id, Long value) {
		try {
//			return this.returnOk(Json.toJson(getService().updateOptimal(id, value)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result updatePriority(UUID id, Integer value) {
		try {
//			return this.returnOk(Json.toJson(getService().updatePriority(id, value)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result updatePrices(UUID id) {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(getService().updatePrices(id, body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result lock() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.manageLock(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result expires() {
		try {
			JsonNode body = request().body().asJson();
			return this.returnOk(Json.toJson(appService.expires(body)));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result findWithOrderId(UUID id) {
		try {
//			return this.returnOk(Json.toJson(getService().findWithOrderId(id)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@BodyParser.Of(BodyParser.Empty.class)
	public Result dehistorizes(UUID id) {
		try {
			return this.returnOk(Json.toJson(getService().dehistorizes(id)));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}
}
