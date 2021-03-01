package com.hongye.service.handler;

import com.hongye.entity.Order;
import com.hongye.enums.OrderAction;
import com.hongye.enums.OrderType;
import com.hongye.model.OrderModel;
import com.hongye.service.processor.ActionProcessor;
import org.springframework.stereotype.Component;

/**
 * @author hongye
 */
@Component("ProductOrderHandler")
public class ProductOrderHandler extends OrderHandler {

	@Override
	public Order handleInternal(OrderAction action, OrderType orderType, OrderModel orderDef, Order currentOrder) throws Exception {
		return  ActionProcessor.process(action,orderType,orderDef,currentOrder);
	}
}
