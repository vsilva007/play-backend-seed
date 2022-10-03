package daos;

import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import models.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DashboardDAO extends BaseDAO {
	@Override
	public Class<?> getEntityClass() {
		return null;
	}

//	public DateAggregate[] findOrdersCreatedByDay() {
//		String sql = "SELECT date_trunc('day', created) AS date, count(1) AS count FROM pdb_order " + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findOrdersCompletedByDay() {
//		String sql = "SELECT date_trunc('day', updated) AS date, count(1) AS count FROM pdb_order " + "WHERE status = " + Order.STATUS_COMPLETE + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findChargeOrdersCreatedByDay() {
//		String sql = "SELECT date_trunc('day', created) AS date, count(1) AS count FROM pdb_charge_order " + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findChargeOrdersCompletedByDay() {
//		String sql = "SELECT date_trunc('day', updated) AS date, count(1) AS count FROM pdb_charge_order " + "WHERE status = " + ChargeOrder.STATUS_COMPLETE + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findPlanningsCreatedByDay() {
//		String sql = "SELECT date_trunc('day', created) AS date, count(1) AS count FROM pdb_planning " + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findPlanningsCompletedByDay() {
//		String sql = "SELECT date_trunc('day', updated) AS date, count(1) AS count FROM pdb_planning " + "WHERE status = " + Planning.STATUS_COMPLETED + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public DateAggregate[] findPlanningsIncidenceByDay() {
//		String sql = "SELECT date_trunc('day', updated) AS date, count(1) AS count FROM pdb_planning " + "WHERE status = " + Planning.STATUS_INCIDENCE + "GROUP BY 1 ORDER BY 1";
//		return getDateAggregates(sql);
//	}
//
//	public StatusAggregate[] findOrderByStatus() {
//		String sql = "SELECT status, count(status) AS count FROM pdb_order GROUP BY status ORDER BY status";
//		return getStatusAggregates(sql);
//	}
//
//	public StatusAggregate[] findChargeOrderByStatus() {
//		String sql = "SELECT status, count(status) AS count FROM pdb_charge_order GROUP BY status ORDER BY status";
//		return getStatusAggregates(sql);
//	}
//
//	public StatusAggregate[] findPlanningByStatus() {
//		String sql = "SELECT status, count(status) AS count FROM pdb_planning GROUP BY status ORDER BY status";
//		return getStatusAggregates(sql);
//	}
//
//	@NotNull
//	private StatusAggregate[] getStatusAggregates(String sql) {
//		RawSql rawSql = RawSqlBuilder.parse(sql).columnMapping("status", "status").columnMapping("count", "count").create();
//		Query<StatusAggregate> query = Ebean.getServer("default").find(StatusAggregate.class);
//		query.setRawSql(rawSql);
//		List<StatusAggregate> modelList = new ArrayList<>();
//		modelList = query.findList();
//		return modelList.toArray(new StatusAggregate[modelList.size()]);
//	}
//
//	@NotNull
//	private DateAggregate[] getDateAggregates(String sql) {
//		RawSql rawSql = RawSqlBuilder.parse(sql).columnMapping("date", "date").columnMapping("count", "count").create();
//		Query<DateAggregate> query = Ebean.getServer("default").find(DateAggregate.class);
//		query.setRawSql(rawSql);
//		List<DateAggregate> modelList = new ArrayList<>();
//		modelList = query.findList();
//		return modelList.toArray(new DateAggregate[modelList.size()]);
//	}
}
