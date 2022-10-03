package controllers;

import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import services.DashboardService;

import javax.inject.Inject;

public class DashboardController extends BaseController {
	private DashboardService appService = null;

	@Inject
	public DashboardController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	DashboardService getService() {
		return new DashboardService();
	}

//	public Result createdOrdersByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.createdOrdersByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result completedOrdersByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.completedOrdersByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result createdChargeOrdersByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.createdChargeOrdersByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result completedChargeOrdersByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.completedChargeOrdersByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result createdPlannnigsByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.createdPlannnigsByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result completedPlanningsByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.completedPlanningsByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result incidencePlanningsByDay() {
//		try {
//			return this.returnOk(Json.toJson(appService.incidencePlanningsByDay()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result orderByStatus() {
//		try {
//			return this.returnOk(Json.toJson(appService.orderByStatus()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result chargeOrderByStatus() {
//		try {
//			return this.returnOk(Json.toJson(appService.chargeOrderByStatus()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result planningByStatus() {
//		try {
//			return this.returnOk(Json.toJson(appService.planningByStatus()));
//		} catch (Exception e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
}
