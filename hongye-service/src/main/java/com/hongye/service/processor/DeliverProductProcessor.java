package com.hongye.service.processor;

import com.hongye.entity.Order;
import com.hongye.mapper.OrderMapper;
import com.hongye.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单发货处理器
 * @author hongye
 */
@Component("deliverProductProcessor")
public class DeliverProductProcessor extends ActionProcessor{

	@Override
	public Order process(OrderModel orderDef, Order currentOrder) throws Exception {
		return this.deliver(orderDef,currentOrder);
	}

	@Autowired
	OrderMapper orderMapper;
	/**
	 * 订单发货
	 * @param currentOrder
	 * @return
	 */
	 private Order deliver(OrderModel orderModel, Order currentOrder) throws Exception {
		//更新订单信息
		currentOrder.setOrderStatus(orderModel.getOrderStatus());
		currentOrder.setCreateTime(System.currentTimeMillis());
		 orderMapper.updateById(currentOrder);
		return currentOrder;
	}
}
