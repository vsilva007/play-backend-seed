package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import daos.ChargeOrderDAO;
import exceptions.BadRequestException;
import exceptions.DateValidationException;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import models.*;
import org.jetbrains.annotations.NotNull;
import play.Logger;
import utils.DateUtils;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.*;

// THIS CLASS SERVICE IS JUST A SAMPLE
public class ChargeOrderService extends BaseService<ChargeOrderDAO, ChargeOrder> {
	public ChargeOrderDAO dao;
//	private ProductService productService;
//	private ProviderService providerService;
//	private OrderService orderService;
//	private TerminalService terminalService;
//	private CaeService caeService;
//	private PlanningService planningService;
//	private UserService userService;
//	private SequenceService sequenceService;

	public ChargeOrderService(boolean onlyDao) {
		this.dao = new ChargeOrderDAO();
	}

	public ChargeOrderService() {
		this.dao = new ChargeOrderDAO();
//		this.productService = new ProductService();
//		this.providerService = new ProviderService();
//		this.orderService = new OrderService();
//		this.terminalService = new TerminalService();
//		this.caeService = new CaeService();
//		this.planningService = new PlanningService();
//		this.userService = new UserService(true);
//		this.sequenceService = new SequenceService();
	}

	@Override
	ChargeOrderDAO getDAO() {
		return this.dao;
	}
	//    public List<ChargeOrder> findNoHistorified() {
	//        List<ChargeOrder> chargeOrderList = (List<ChargeOrder>)(List<?>) this.getDAO().findNoHistorified();
	//        chargeOrderList.sort(Comparator.comparing(ChargeOrder::getCreated));
	//        return chargeOrderList;
	//    }

//	public List<ChargeOrder> findHistorified(JsonNode body) throws ObjectNotFoundException, BadRequestException {
//		RangeDateAdapter rda = new RangeDateAdapter((ObjectNode) body);
//		User user = (User) this.userService.findById(rda.getUserId());
//		List<ChargeOrder> chargeOrderList = new ArrayList<>();
//		if (user.getBasesList().size() > 0) {
//			chargeOrderList = this.getDAO().findAllWithBases(user.getBasesList(), true, DateUtils.getDayStart(rda.getStart().toInstant().getEpochSecond()), DateUtils.getDayEnd(rda.getEnd().toInstant().getEpochSecond()));
//		} else {
//			chargeOrderList = (List<ChargeOrder>) (List<?>) this.getDAO().findHistorified(DateUtils.getDayStart(rda.getStart().toInstant().getEpochSecond()), DateUtils.getDayEnd(rda.getEnd().toInstant().getEpochSecond()));
//		}
//		chargeOrderList.sort(Comparator.comparing(ChargeOrder::getCreated));
//		return chargeOrderList;
//	}

//	public List<ChargeOrder> findAll(JsonNode body) throws ObjectNotFoundException, BadRequestException {
//		RangeDateAdapter rda = new RangeDateAdapter((ObjectNode) body);
//		User user = (User) this.userService.findById(rda.getUserId());
//		List<ChargeOrder> chargeOrderList = new ArrayList<>();
//		if (user.getBasesList().size() > 0) {
//			chargeOrderList = this.getDAO().findAllWithBases(user.getBasesList(), false, DateUtils.getDayStart(rda.getStart().toInstant().getEpochSecond()), DateUtils.getDayEnd(rda.getEnd().toInstant().getEpochSecond()));
//		} else {
//			chargeOrderList = this.getDAO().findNoHistorified(DateUtils.getDayStart(rda.getStart().toInstant().getEpochSecond()), DateUtils.getDayEnd(rda.getEnd().toInstant().getEpochSecond()));
//		}
//		chargeOrderList.sort(Comparator.comparing(ChargeOrder::getCreated));
//		return chargeOrderList;
//	}

//	public ChargeOrder create(JsonNode body) throws ObjectAlreadyExistException, BadRequestException, ObjectNotFoundException {
//		ChargeOrder chargeOrder = new ChargeOrder((ObjectNode) body);
//		if (chargeOrder.id == null)
//			chargeOrder.id = UUID.randomUUID();
//		this.validateRelatedEntities(chargeOrder);
//		//Sets the friendly id
//		try {
//			chargeOrder.fid = ChargeOrder.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(ChargeOrder.class.getSimpleName()).value;
//		} catch (Exception ex) {
//			throw new BadRequestException(ex.getMessage());
//		}
//		// Check if order exists and assign it
//		chargeOrder = this.checkAndAssignOrder(chargeOrder);
//		// Check if charge order exists
//		try {
//			this.dao.findById(chargeOrder.id);
//			throw new ObjectAlreadyExistException(String.valueOf(chargeOrder.id));
//		} catch (ObjectNotFoundException ex) {
//			// nothing to do
//		}
//		// Check duplicates
//		chargeOrder = this.checkDuplicates(chargeOrder);
//		// Create charge order
//		this.save(chargeOrder);
//		try {
//			orderService.evaluateAndUpdateOrderStatus(chargeOrder.getOrder());
//		} catch (BadRequestException ex) {
//			this.delete(chargeOrder.getId());
//			throw ex;
//		}
//		// Check charge order expires by date
//		this.checkChargeOrderExpiresByDate(chargeOrder);
//		return chargeOrder;
//	}

//	public ChargeOrder update(JsonNode body) throws ObjectNotFoundException, BadRequestException, DateValidationException {
//		boolean fromOrderPlanified = false;
//		ChargeOrder chargeOrder = new ChargeOrder((ObjectNode) body);
//		if (chargeOrder.id == null)
//			throw new ObjectNotFoundException(String.valueOf(chargeOrder.id));
//		this.validateRelatedEntities(chargeOrder);
//		if (chargeOrder.getOptimalUseDate() != null && chargeOrder.getOptimalUseDate() > chargeOrder.getExpiration())
//			throw new DateValidationException(chargeOrder.getId().toString());
//		ChargeOrder current = (ChargeOrder) this.dao.findById(chargeOrder.id);
//		ChargeOrder currentBck = (ChargeOrder) this.dao.findById(chargeOrder.id);
//		boolean isScheduled = this.hasOrderAndPlanning(current);
//		if (isScheduled && current.getStatus() != ChargeOrder.STATUS_EXPIRED)
//			throw new BadRequestException("Cannot update. Related order is assigned to planning");
//		Order currentOrder = current.getOrder();
//		// Check if chargeOrder has previously an assigned order
//		this.checkAndUnAssignOrderWithCurrent(chargeOrder, current);
//		current.byChargeOrder(chargeOrder);
//		// Check duplicates
//		current = this.checkDuplicates(current);
//		// Check if order exists and assign it
//		if (!isScheduled)
//			current = this.checkAndAssignOrder(current);
//		else
//			current.setStatus(ChargeOrder.STATUS_PLANIFIED);
//		if (current.getOrder() != null)
//			fromOrderPlanified = current.getOrder().getStatus() == Order.STATUS_PLANIFIED_NO_OC || current.getOrder().getStatus() == Order.STATUS_PLANIFIED_CONSTRUCTION;
//		this.update(current);
//		if (!isScheduled) {
//			UUID currentOrderId = UUID.randomUUID();
//			if (current.getOrder() != null)
//				currentOrderId = current.getOrder().getId();
//			if (currentOrder != null && currentOrder.getId().compareTo(currentOrderId) != 0)
//				orderService.evaluateAndUpdateOrderStatus(currentOrder);
//			orderService.evaluateAndUpdateOrderStatus(current.getOrder());
//		}
//		// Delete duplicates for old code
//		this.deleteDuplicates(currentBck);
//		// Check order expires
//		this.checkOrderExpiresByStatus(current, currentBck);
//		// Check order expires by date
//		this.checkChargeOrderExpiresByDate(current);
//		// Check order locked previously
//		this.verifyLock(current, currentBck);
//		// Evaluate planning status after assigning a charge order to an already planified order
//		this.planningService.evaluatePlanningStatus(fromOrderPlanified, current);
//		return current;
//	}

//	private void checkChargeOrderExpiresByDate(ChargeOrder chargeOrder) {
//		if (chargeOrder.getExpiration() < new Date().getTime()) {
//			chargeOrder.setStatus(ChargeOrder.STATUS_EXPIRED);
//			this.update(chargeOrder);
//		}
//	}

//	private void checkOrderExpiresByStatus(ChargeOrder current, ChargeOrder currentBck) {
//		if (currentBck.getExpiration() == current.getExpiration() && currentBck.getStatus() == ChargeOrder.STATUS_EXPIRED) {
//			current.setStatus(ChargeOrder.STATUS_EXPIRED);
//			this.update(current);
//		}
//	}

//	private ChargeOrder checkDuplicates(ChargeOrder chargeOrder) {
//		ChargeOrder[] duplicates = this.getDAO().findEqual(chargeOrder);
//		boolean duplicated = duplicates.length > 0;
//		chargeOrder.setDuplicated(duplicated);
//		for (ChargeOrder co : duplicates) {
//			if (((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_NEW || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_ASSIGNED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_LOCKED
//					|| ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_EXPIRED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_CANCELLED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_ERROR) {
//				((ChargeOrder) co).setDuplicated(duplicated);
//				this.update(co);
//			}
//		}
//		return chargeOrder;
//	}

//	private void deleteDuplicates(ChargeOrder chargeOrder) {
//		ChargeOrder[] duplicates = this.getDAO().findEqual(chargeOrder);
//		boolean duplicated = duplicates.length > 1;
//		for (ChargeOrder co : duplicates) {
//			if (((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_NEW || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_ASSIGNED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_LOCKED
//					|| ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_EXPIRED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_CANCELLED || ((ChargeOrder) co).getStatus() == ChargeOrder.STATUS_ERROR) {
//				((ChargeOrder) co).setDuplicated(duplicated);
//				this.update(co);
//			}
//		}
//	}

//	public UUID delete(UUID id) throws ObjectNotFoundException, BadRequestException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		if (this.hasOrderAndPlanning(chargeOrder))
//			throw new BadRequestException("Cannot update. Related order is assigned to planning");
//		// Check if chargeOrder has previously an assigned order
//		this.checkAndUnAssignOrder(chargeOrder);
//		if (chargeOrder == null)
//			throw new ObjectNotFoundException(ChargeOrder.class.getSimpleName(), String.valueOf(id));
//		this.deleteById(id);
//		orderService.evaluateAndUpdateOrderStatus(chargeOrder.getOrder());
//		return id;
//	}

//	private void validateRelatedEntities(ChargeOrder chargeOrder) throws BadRequestException {
//		// Check if product exists
//		try {
//			for (ChargeOrderProduct product : chargeOrder.getProducts())
//				productService.findById(product.product.id);
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//		// Check if provider exists
//		try {
//			providerService.findById(chargeOrder.provider.id);
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//		// Check if terminal exists
//		try {
//			terminalService.findById(chargeOrder.terminal.id);
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//		// Check if cae exists
//		try {
//			caeService.findById(chargeOrder.cae.id);
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//	}

//	private ChargeOrder checkAndAssignOrder(ChargeOrder chargeOrder) throws BadRequestException {
//		try {
//			if (chargeOrder.getOrder() != null && !chargeOrder.getOrder().getId().equals((UUID.fromString(Order.DEFAULT_UUID)))) {
//				Order order = (Order) orderService.findById(chargeOrder.getOrder().getId());
//				order.getChargeOrders().add(chargeOrder);
//				// Current order is planified without charge order
//				if (order.getStatus() == Order.STATUS_PLANIFIED_NO_OC || order.getStatus() == Order.STATUS_PLANIFIED_CONSTRUCTION) {
//					chargeOrder.setStatus(ChargeOrder.STATUS_PLANIFIED);
//				} else {
//					chargeOrder.setStatus(ChargeOrder.STATUS_ASSIGNED);
//				}
//				orderService.update(order);
//				chargeOrder.setOrder(order);
//			} else {
//				chargeOrder.setOrder(null);
//				chargeOrder.setStatus(ChargeOrder.STATUS_NEW);
//			}
//			return chargeOrder;
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//	}

//	private void checkAndUnAssignOrderWithCurrent(ChargeOrder chargeOrder, ChargeOrder current) throws BadRequestException {
//		try {
//			if (current.getOrder() != null && !chargeOrder.getOrder().getId().equals(current.getOrder().getId())) {
//				Order order = (Order) orderService.findById(current.getOrder().getId());
//				order.getChargeOrders().remove(current);
//				orderService.update(order);
//			}
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//	}

//	private void checkAndUnAssignOrder(ChargeOrder chargeOrder) throws BadRequestException {
//		try {
//			if (chargeOrder.getOrder() != null) {
//				Order order = (Order) orderService.findById(chargeOrder.getOrder().getId());
//				order.getChargeOrders().remove(chargeOrder);
//				orderService.update(order);
//			}
//		} catch (ObjectNotFoundException ex) {
//			Logger.debug(ex.getMessage(), ex);
//			throw new BadRequestException(ex.getMessage());
//		}
//	}

//	private boolean hasOrderAndPlanning(ChargeOrder current) {
//		if (current.getOrder() == null)
//			return false;
//		try {
//			this.planningService.findWithOrderId(current.getOrder().getId());
//		} catch (ObjectNotFoundException ex) {
//			return false;
//		}
//		return true;
//	}

//	public ChargeOrder[] findWithOrderId(UUID id) throws ObjectNotFoundException {
//		return this.getDAO().findWithOrderId(id);
//	}

//	public List<ChargeOrder> findAssignable(UUID userId) throws ObjectNotFoundException {
//		User user = (User) this.userService.findById(userId);
//		List<ChargeOrder> chargeOrderList = new ArrayList<>();
//		//        if (user.getBasesList().size() > 0) {
//		//            chargeOrderList = this.getDAO().findAssignable(user.getBasesList());
//		//        } else {
//		chargeOrderList = (List<ChargeOrder>) (List<?>) this.getDAO().findAssignable();
//		//        }
//		return chargeOrderList;
//	}

//	public ChargeOrder updateNotes(UUID id, String value) throws ObjectNotFoundException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		chargeOrder.setNotes(value);
//		this.update(chargeOrder);
//		return chargeOrder;
//	}

//	public ChargeOrder updateModality(UUID id, String value) throws ObjectNotFoundException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		chargeOrder.setModality(value);
//		this.update(chargeOrder);
//		return chargeOrder;
//	}

//	public Object updateExpiration(UUID id, long value) throws ObjectNotFoundException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		chargeOrder.setExpiration(value);
//		if (chargeOrder.getOptimalUseDate() != null && chargeOrder.getOptimalUseDate() > chargeOrder.getExpiration())
//			chargeOrder.setOptimalUseDate(chargeOrder.getExpiration());
//		return evalChargeOrderStatus(chargeOrder);
//	}

//	@NotNull
//	private Object evalChargeOrderStatus(ChargeOrder chargeOrder) {
//		if (chargeOrder.getStatus() == ChargeOrder.STATUS_LOCKED)
//			// do nothing :D
//			;
//		else if (chargeOrder.getExpiration() < new Date().getTime())
//			chargeOrder.setStatus(ChargeOrder.STATUS_EXPIRED);
//		else if (chargeOrder.getOrder() != null && chargeOrder.getOrder().getStatus() == Order.STATUS_COMPLETE)
//			chargeOrder.setStatus(ChargeOrder.STATUS_COMPLETE);
//		else if (this.hasOrderAndPlanning(chargeOrder))
//			chargeOrder.setStatus(ChargeOrder.STATUS_PLANIFIED);
//		else if (chargeOrder.getOrder() != null)
//			chargeOrder.setStatus(ChargeOrder.STATUS_ASSIGNED);
//		else
//			chargeOrder.setStatus(ChargeOrder.STATUS_NEW);
//		this.update(chargeOrder);
//		return chargeOrder;
//	}

//	public Object updateOptimal(UUID id, long value) throws ObjectNotFoundException, DateValidationException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		if (value > chargeOrder.getExpiration())
//			throw new DateValidationException(chargeOrder.getId().toString());
//		if (value == 99999)
//			chargeOrder.setOptimalUseDate(null);
//		else
//			chargeOrder.setOptimalUseDate(value);
//		this.update(chargeOrder);
//		return chargeOrder;
//	}

//	public Object updatePriority(UUID id, int value) throws ObjectNotFoundException {
//		ChargeOrder chargeOrder = (ChargeOrder) this.findById(id);
//		if (value == 99999)
//			chargeOrder.setPriority(null);
//		else
//			chargeOrder.setPriority((byte) value);
//		this.update(chargeOrder);
//		return chargeOrder;
//	}

//	public ChargeOrder updatePrices(UUID id, JsonNode body) throws BadRequestException, ObjectNotFoundException {
//		ChargeOrder chargeOrder = new ChargeOrder((ObjectNode) body);
//		ChargeOrder current = (ChargeOrder) this.findById(id);
//		current.setProducts(chargeOrder.getProducts());
//		this.update(current);
//		return current;
//	}

//	public ChargeOrder manageLock(JsonNode body) throws ObjectNotFoundException, BadRequestException {
//		ChargeOrder chargeOrder = new ChargeOrder((ObjectNode) body);
//		if (chargeOrder.id == null)
//			throw new ObjectNotFoundException(String.valueOf(chargeOrder.id));
//		ChargeOrder current = (ChargeOrder) this.dao.findById(chargeOrder.id);
//		if (current.getStatus() == ChargeOrder.STATUS_LOCKED) {
//			// let eval status do the job
//			current.setStatus(ChargeOrder.STATUS_NEW);
//			this.evalChargeOrderStatus(current);
//		} else
//			current.setStatus(ChargeOrder.STATUS_LOCKED);
//		this.update(current);
//		return current;
//	}

	private ChargeOrder verifyLock(ChargeOrder current, ChargeOrder chargeOrder) {
		if (chargeOrder.getStatus() == ChargeOrder.STATUS_LOCKED)
			current.setStatus(ChargeOrder.STATUS_LOCKED);
		this.update(current);
		return current;
	}

	public ChargeOrder expires(JsonNode body) throws ObjectNotFoundException, BadRequestException {
		ChargeOrder chargeOrder = new ChargeOrder((ObjectNode) body);
		if (chargeOrder.id == null)
			throw new ObjectNotFoundException(String.valueOf(chargeOrder.id));
		ChargeOrder current = (ChargeOrder) this.dao.findById(chargeOrder.id);
		current.setStatus(ChargeOrder.STATUS_EXPIRED);
		this.update(current);
		return current;
	}

	public void checkAutomaticExpiration() {
		ChargeOrder[] chargerOrders = this.getDAO().findExpired(new Date().getTime());
		if (chargerOrders.length == 0)
			return;
		for (ChargeOrder chargeOrder : (ChargeOrder[])chargerOrders) {
			chargeOrder.setStatus(ChargeOrder.STATUS_EXPIRED);
			this.update(chargeOrder);
		}
	}

	public void historicize() {
		LocalDateTime date = LocalDateTime.now().minusDays(7);
		LocalDateTime date2 = LocalDateTime.now().minusDays(30);
		ChargeOrder[] chargerOrders = this.getDAO().findHistoricizeCandidates(date.atZone(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond(), date2.atZone(ZoneId.of("Europe/Paris")).toInstant().getEpochSecond());
		if (chargerOrders.length == 0)
			return;
		for (ChargeOrder chargeOrder : (ChargeOrder[])chargerOrders) {
			chargeOrder.setHistorified(true);
			this.update(chargeOrder);
		}
	}

	public UUID dehistorizes(UUID id) throws ObjectNotFoundException {
		return this.getDAO().dehistorizes(id);
	}
}
