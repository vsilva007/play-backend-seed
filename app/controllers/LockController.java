package controllers;

import services.LockService;

import javax.inject.Inject;

public class LockController extends BaseController {
	private LockService appService = null;

	@Inject
	public LockController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	LockService getService() {
		return new LockService();
	}
}
