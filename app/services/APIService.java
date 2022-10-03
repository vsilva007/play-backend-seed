package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import daos.BaseDAO;
import exceptions.AppException;
import exceptions.BadRequestException;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import models.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class APIService extends BaseService<BaseDAO<BaseModel>, BaseModel> {
//	private OrderService orderService;

	public APIService() {
//		this.orderService = new OrderService();
	}
	@Override
	BaseDAO<BaseModel> getDAO() {
		return null;
	}

//	public Order createOrder(JsonNode body) throws ObjectAlreadyExistException, BadRequestException {
//		Order order = new Order((ObjectNode) body);
//		if (order.id == null)
//			order.id = UUID.randomUUID();
//		//DONT - Sets the friendly id
//		//		try {
//		//			order.fid = Order.SEQ_PREFIX + "_" + Year.now().getValue() + "_" + sequenceService.nextValue(Order.class.getSimpleName()).value;
//		//		} catch (Exception ex) {
//		//			throw new BadRequestException(ex.getMessage());
//		//		}
//		// Check if order exists
//		try {
//			this.orderService.findByUUID(order.uuid);
//			throw new ObjectAlreadyExistException(String.valueOf(order.uuid));
//		} catch (ObjectNotFoundException ex) {
//			// nothing to do
//		}
//		// Set default status
//		order.status = 0;
//		// Create order
//		orderService.save(order);
//		return order;
//	}
//
//	public String deleteOrder(String uuid) throws ObjectNotFoundException, BadRequestException {
//		Order order = this.orderService.findByUUID(uuid);
//		if (this.orderService.hasChargeOrders(order))
//			throw new BadRequestException("Cannot delete order with assigned charge orders");
//		if (order != null) {
//			return this.orderService.deleteByUUID(uuid);
//		}
//		throw new ObjectNotFoundException(Order.class.getSimpleName(), uuid);
//	}
//
//	public Order getOrder(String uuid) throws ObjectNotFoundException {
//		return this.orderService.findByUUID(uuid);
//	}
//
//	public List<Order> getOrderList() {
//		return orderService.findAll();
//	}
}
