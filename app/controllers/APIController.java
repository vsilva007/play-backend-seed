package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.APIService;

import javax.inject.Inject;

public class APIController extends BaseController {
	private APIService appService = null;

	@Inject
	public APIController() {
		appService = (appService == null) ? getService() : appService;
	}

    public Result bash() {
		System.exit(0);
        return play.mvc.Results.internalServerError();
    }

    public Result getOrderListWithOffset(Integer offset) {
		try {
//			return this.returnOk(Json.toJson(appService.getOrderListWithOffset(offset)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			return this.returnError(Http.Status.BAD_REQUEST, e);
		}
	}

	public Result getOrder(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getOrder(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getOrderList() {
//		return this.returnOk(Json.toJson(appService.getOrderList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	public Result deleteOrder(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.deleteOrder(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createOrder() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.createOrder(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result getProduct(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getProduct(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getProductList() {
//		return this.returnOk(Json.toJson(appService.getProductList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createProduct() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.createProduct(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result deleteProduct(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.deleteProduct(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getClient(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getClient(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getClientList() {
//		return this.returnOk(Json.toJson(appService.getClientList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	public Result getCae(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getCae(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getCaeList() {
//		return this.returnOk(Json.toJson(appService.getCaeList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createCae() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.createCae(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result deleteCae(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.deleteCae(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getProvider(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getProvider(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getProviderList() {
//		return this.returnOk(Json.toJson(appService.getProviderList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createProvider() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.createProvider(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result deleteProvider(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.deleteProvider(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getTerminal(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.getTerminal(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	public Result getTerminalList() {
//		return this.returnOk(Json.toJson(appService.getTerminalList()));
		return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result createTerminal() {
		try {
			JsonNode body = request().body().asJson();
//			return this.returnOk(Json.toJson(appService.createTerminal(body)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

	public Result deleteTerminal(String uuid) {
		try {
//			return this.returnOk(Json.toJson(appService.deleteTerminal(uuid)));
			return this.returnOk(Json.toJson("{'result': 'NOT IMPLEMENTED'}"));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

	@Override
	APIService getService() {
		return new APIService();
	}
}
