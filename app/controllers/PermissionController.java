package controllers;

import services.PermissionService;

import javax.inject.Inject;

public class PermissionController extends BaseController {
	private PermissionService appService = null;

	@Inject
	public PermissionController() {
		appService = (appService == null) ? getService() : appService;
	}

	@Override
	PermissionService getService() {
		return new PermissionService();
	}
}
