package daos;

import exceptions.ObjectNotFoundException;
import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import models.ChargeOrder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static io.ebean.Expr.*;

// THIS DAO CLASS IS A SAMPLE
public class ChargeOrderDAO extends BaseDAO<ChargeOrder> {
	Calendar cal = Calendar.getInstance();

	@Override
	public Class<?> getEntityClass() {
		return ChargeOrder.class;
	}

	public ChargeOrder[] findWithOrderId(UUID id) throws ObjectNotFoundException {
		List<ChargeOrder> modelList = (List<ChargeOrder>) this.find.query().where().eq("order_id", id).findList();
		if (modelList != null) {
			return modelList.toArray(new ChargeOrder[modelList.size()]);
		} else {
			throw new ObjectNotFoundException(getEntityClass().getSimpleName());
		}
	}

	public ChargeOrder[] findEqual(ChargeOrder chargeOrder) {
		List<ChargeOrder> modelList;
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		modelList = (List<ChargeOrder>) this.find.query()
				.where()
				.and()
				.ne("id", chargeOrder.getId())
				.ieq("code", (chargeOrder.getCode()))
				.contains("fid", "_" + cal.get(Calendar.YEAR) + "_")
				.lt("expiration", cal.getTimeInMillis()).findList();

		return modelList.toArray(new ChargeOrder[modelList.size()]);
	}

	public List<ChargeOrder> findAssignable() {
		List<ChargeOrder> modelList = (List<ChargeOrder>) this.find.query().where().and().eq("status", ChargeOrder.STATUS_NEW).eq("duplicated", false).ne("code", "").findList();
		return modelList;
	}
	//    public List<ChargeOrder> findAssignable(List<Bases> basesList) {
	//
	//        ArrayList<String> baseIdsList = new ArrayList<String>();
	//        for (Bases base : basesList){
	//            baseIdsList.add("'" + String.valueOf(base.id) + "'");
	//        }
	//
	//        String sql = "SELECT id, fid, code, order_id, provider_id, expiration, terminal_id, cae_id, status, " +
	//                "duplicated, created, updated, notes, modality FROM pdb_charge_order where order_id IN (" +
	//                "  SELECT id FROM pdb_order WHERE id IN (" +
	//                "    SELECT order_id FROM pdb_order_client WHERE location_id IN (" +
	//                "      SELECT pdb_location_id" +
	//                "      FROM pdb_location_pdb_bases" +
	//                "      WHERE pdb_bases_id IN ("+String.join(",", baseIdsList)+")" +
	//                "    )" +
	//                "  ) AND status = "+ChargeOrder.STATUS_NEW+" AND duplicated = false AND code != ''" +
	//                ") OR (order_id IS NULL AND status = "+ChargeOrder.STATUS_NEW+" AND duplicated = false AND code != '');";
	//
	//        RawSql rawSql = RawSqlBuilder
	//                .parse(sql)
	//                .create();
	//
	//        Query<ChargeOrder> query = Ebean.getServer("default").find(ChargeOrder.class);
	//        query.setRawSql(rawSql);
	//
	//        List<ChargeOrder> modelList = new ArrayList<ChargeOrder>();
	//        modelList = query.findList();
	//
	//        return modelList;
	//    }

	public ChargeOrder[] findExpired(long date) {
		List<ChargeOrder> modelList = (List<ChargeOrder>) this.find.query().where().and().or(eq("status", ChargeOrder.STATUS_NEW), eq("status", ChargeOrder.STATUS_ASSIGNED)).le("expiration", date).findList();
		return modelList.toArray(new ChargeOrder[modelList.size()]);
	}

//	public List<ChargeOrder> findAllWithBases(List<Bases> basesList) {
//		ArrayList<String> baseIdsList = new ArrayList<String>();
//		for (Bases base : basesList) {
//			baseIdsList.add("'" + base.id + "'");
//		}
//		String sql = "SELECT id, fid, code, order_id, provider_id, expiration, terminal_id, cae_id, status, " + "duplicated, created, updated, notes, modality, historified FROM pdb_charge_order WHERE order_id IN ("
//				+ "  SELECT id FROM pdb_order WHERE id IN (" + "    SELECT order_id FROM pdb_order_client WHERE location_id IN (" + "      SELECT pdb_location_id" + "      FROM pdb_location_pdb_bases" + "      WHERE pdb_bases_id IN (" + String
//				.join(",", baseIdsList) + ")" + "    )" + "  )" + ") OR order_id IS NULL AND historified = false";
//		RawSql rawSql = RawSqlBuilder.parse(sql).create();
//		Query<ChargeOrder> query = Ebean.getServer("default").find(ChargeOrder.class);
//		query.setRawSql(rawSql);
//		List<ChargeOrder> modelList = new ArrayList<ChargeOrder>();
//		modelList = query.findList();
//		return modelList;
//	}

//	public List<ChargeOrder> findAllWithBases(List<Bases> basesList, boolean historified, long start, long end) {
//		ArrayList<String> baseIdsList = new ArrayList<String>();
//		for (Bases base : basesList) {
//			baseIdsList.add("'" + base.id + "'");
//		}
//		String sql = "SELECT id, fid, code, order_id, provider_id, expiration, terminal_id, cae_id, status, " + "duplicated, created, updated, notes, modality, historified FROM pdb_charge_order WHERE order_id IN ("
//				+ "  SELECT id FROM pdb_order WHERE id IN (" + "    SELECT order_id FROM pdb_order_client WHERE location_id IN (" + "      SELECT pdb_location_id" + "      FROM pdb_location_pdb_bases" + "      WHERE pdb_bases_id IN (" + String
//				.join(",", baseIdsList) + ")" + "    )" + "  )" + ") AND historified = " + historified;
//		if (historified)
//			sql = sql.concat(" AND extract(epoch from created) >= " + start + " AND extract(epoch from created) <= " + end);
//		RawSql rawSql = RawSqlBuilder.parse(sql).create();
//		Query<ChargeOrder> query = Ebean.getServer("default").find(ChargeOrder.class);
//		query.setRawSql(rawSql);
//		List<ChargeOrder> modelList = new ArrayList<ChargeOrder>();
//		modelList = query.findList();
//		return modelList;
//	}

	public List<ChargeOrder> findNoHistorified(long start, long end) {
		String sql = "SELECT id, fid, code, order_id, provider_id, expiration, terminal_id, cae_id, status, " + "duplicated, created, updated, notes, modality, historified FROM pdb_charge_order "
				+ "WHERE historified = false AND extract(epoch from created) >= " + start + " AND extract(epoch from created) <= " + end;
		RawSql rawSql = RawSqlBuilder.parse(sql).create();
		Query<ChargeOrder> query = Ebean.getServer("default").find(ChargeOrder.class);
		query.setRawSql(rawSql);
		List<ChargeOrder> modelList = new ArrayList<ChargeOrder>();
		modelList = query.findList();
		return modelList;
	}

	public List<ChargeOrder> findHistorified(long start, long end) {
		List<ChargeOrder> modelList = (List<ChargeOrder>) this.find.query().where().and().eq("historified", true).ge("extract(epoch from created)", start).le("extract(epoch from created)", end).findList();
		return modelList;
	}

	public ChargeOrder[] findHistoricizeCandidates(long date, long date2) {
		List<ChargeOrder> modelList = (List<ChargeOrder>) this.find.query().where().and()
				.or(and(eq("status", ChargeOrder.STATUS_COMPLETE), le("extract(epoch from updated)", date)), and(eq("status", ChargeOrder.STATUS_EXPIRED), le("extract(epoch from updated)", date2))).eq("historified", false).findList();
		return modelList.toArray(new ChargeOrder[modelList.size()]);
	}

	public UUID dehistorizes(UUID id) throws ObjectNotFoundException {
		ChargeOrder entity = (ChargeOrder) this.find.byId(id);
		if (entity != null) {
			entity.setHistorified(false);
			update(entity);
			return id;
		} else {
			throw new ObjectNotFoundException(this.getEntityClass().getSimpleName(), String.valueOf(id));
		}
	}
}
