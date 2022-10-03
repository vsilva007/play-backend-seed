package services;

import daos.DashboardDAO;

public class DashboardService extends BaseService {
	public DashboardDAO dao;

	public DashboardService() {
		this.dao = new DashboardDAO();
	}

	public DashboardService(boolean onlyDao) {
		this.dao = new DashboardDAO();
	}

	@Override
	DashboardDAO getDAO() {
		return this.dao;
	}
}
