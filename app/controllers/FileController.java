package controllers;

import exceptions.AppException;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import services.FileService;
import utils.PdbExecutionContext;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class FileController extends BaseController {
	private FileService appService = null;
	private PdbExecutionContext pdbExecutionContext;
	private WSClient ws;

	@Inject
	public FileController(PdbExecutionContext pdbExecutionContext, WSClient ws) {
		this.ws = ws;
		this.pdbExecutionContext = pdbExecutionContext;
		appService = (appService == null) ? getService() : appService;
	}

	private static CompletionStage<String> processResponse() {
		return CompletableFuture.completedFuture("ok");
	}

	@Override
	FileService getService() {
		return new FileService(this.ws, this.pdbExecutionContext);
	}

	@BodyParser.Of(BodyParser.MultipartFormData.class)
	public Result create(boolean append) {
		try {
			return this.returnOk(Json.toJson(appService.create(request().body().asMultipartFormData().getFiles().get(0), append)));
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_ACCEPTABLE, e);
		}
	}

//	public Result checkHeptanDataLoaded() {
//		try {
//			return this.returnOk(Json.toJson(appService.checkHeptanDataLoaded()));
//		} catch (AppException e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result checkVeosatDataLoaded() {
//		try {
//			return this.returnOk(Json.toJson(appService.checkVeosatDataLoaded()));
//		} catch (AppException e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
//
//	public Result getStats() {
//		return this.returnOk(Json.toJson(appService.getStats()));
//	}

	public Result process(int tolerance, Integer reprocess) {
		try {
			return this.returnOk(Json.toJson(appService.process(tolerance, reprocess)));
		} catch (AppException e) {
			Logger.debug(e.getMessage(), e);
			return this.returnError(Http.Status.NOT_FOUND, e);
		}
	}

//	public Result processDaily(Integer tolerance) {
//		try {
//			return this.returnOk(Json.toJson(appService.processDaily(tolerance)));
//		} catch (AppException e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}

//	public Result findNearClients(UUID id) {
//		try {
//			return this.returnOk(Json.toJson(appService.getNearClientsOrderedByDistance(id)));
//		} catch (AppException e) {
//			Logger.debug(e.getMessage(), e);
//			return this.returnError(Http.Status.NOT_FOUND, e);
//		}
//	}
}
