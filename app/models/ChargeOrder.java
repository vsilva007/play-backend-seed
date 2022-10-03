package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.BadRequestException;
import io.ebean.Finder;
import lombok.Getter;
import lombok.Setter;
import play.Logger;
import services.helpers.JsonHelper;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

// THIS CLASS ENTITY IS A SAMPLE

@Entity
@Getter
@Setter
@Table(name = "pdb_charge_order")
public class ChargeOrder extends BaseModel {
	public static final String SEQ_PREFIX = "OC";
	public static final byte STATUS_NEW = 0;
	public static final byte STATUS_ASSIGNED = 1;
	public static final byte STATUS_COMPLETE = 2;
	public static final byte STATUS_ERROR = 4;
	public static final byte STATUS_PLANIFIED = 5;
	public static final byte STATUS_LOCKED = 7;
	public static final byte STATUS_EXPIRED = 8;
	public static final byte STATUS_CANCELLED = 9;
	public static Finder<Long, ChargeOrder> find = new Finder<Long, ChargeOrder>(ChargeOrder.class);
	public String fid;
	public String code;
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	public Set<ChargeOrderProduct> products;
//	@ManyToOne(optional = true)
//	@JsonIgnoreProperties({ "chargeOrders", "cae", "terminal" })
//	public Order order;
//	@ManyToOne
//	@JsonIgnoreProperties({ "terminals" })
//	public Provider provider;
//	public long expiration;
//	@ManyToOne
//	@JsonIgnoreProperties({ "providers" })
//	public Terminal terminal;
//	@ManyToOne
//	public Cae cae;
	public byte status;
	public boolean duplicated;
	public String notes;
	public String modality;
	public boolean historified;
	public Long optimalUseDate;
	public Byte priority;

	public ChargeOrder(String fid, String code,
//					   Set<ChargeOrderProduct> products,
//					   Order order, Provider provider,
					   long expiration,
//					   Terminal terminal,
//					   Cae cae, byte status,
					   boolean duplicated,
					   String notes,
					   String modality,
					   boolean historified,
					   long optimalUseDate,
					   byte priority
	) {
		this.fid = fid;
		this.code = code;
//		this.products = products;
//		this.order = order;
//		this.provider = provider;
//		this.expiration = expiration;
//		this.terminal = terminal;
//		this.cae = cae;
		this.status = status;
		this.duplicated = duplicated;
		this.notes = notes;
		this.modality = modality;
		this.historified = historified;
		this.optimalUseDate = optimalUseDate;
		this.priority = priority;
	}

	public ChargeOrder(ObjectNode json) throws BadRequestException {
		this.createFromJson(json);
	}

	public static List<ChargeOrder> findAll() {
		return ChargeOrder.find.all();
	}

	public void byChargeOrder(ChargeOrder chargeOrder) {
		this.code = chargeOrder.getCode();
//		this.products = chargeOrder.getProducts();
//		this.provider = chargeOrder.getProvider();
//		this.expiration = chargeOrder.getExpiration();
//		this.terminal = chargeOrder.getTerminal();
//		this.cae = chargeOrder.getCae();
		this.status = chargeOrder.getStatus();
		this.duplicated = chargeOrder.isDuplicated();
		this.notes = chargeOrder.getNotes();
		this.modality = chargeOrder.getModality();
		this.historified = chargeOrder.isHistorified();
		this.optimalUseDate = chargeOrder.getOptimalUseDate();
		this.priority = chargeOrder.getPriority();
//		if (!chargeOrder.getOrder().getId().equals((UUID.fromString(Order.DEFAULT_UUID))))
//			this.order = chargeOrder.getOrder();
//		else
//			this.order = null;
	}

	@Override
	// @formatter:off
	public String toString() {
		return "ChargeOrder{" +
 				"fid='" + fid + '\'' +
 				", code='" + code + '\'' +
// 				", products=" + products +
// 				", order=" + order +
// 				", provider=" + provider +
// 				", expiration=" + expiration +
// 				", terminal=" + terminal +
// 				", cae=" + cae +
 				", status="	+ status +
 				", duplicated=" + duplicated +
 				", notes='" + notes + '\'' +
 				", modality='" + modality + '\'' +
 				", historified=" + historified +
 				", optimalUseDate=" + optimalUseDate + ", priority=" + priority + ", id=" + id + '}';
	}
}



