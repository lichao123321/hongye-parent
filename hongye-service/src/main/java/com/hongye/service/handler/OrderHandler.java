package com.hongye.service.handler;

import com.hongye.base.BusinessException;
import com.hongye.entity.Order;
import com.hongye.enums.OrderAction;
import com.hongye.enums.OrderStatus;
import com.hongye.enums.OrderType;
import com.hongye.mapper.OrderMapper;
import com.hongye.model.OrderModel;
import com.hongye.service.SpringContextBeanService;
import com.hongye.util.ComUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author hongye
 */
public abstract class OrderHandler {

	public static OrderHandler getHandler(OrderType orderType) throws Exception{
		if(ComUtil.isEmpty(orderType)){
			throw new BusinessException("订单类型未找到");
		}
		String beanName = orderType.name() + OrderHandler.class.getSimpleName();
		OrderHandler handler = null;
		try{
			handler = SpringContextBeanService.getBean(beanName);
		}catch (NoSuchBeanDefinitionException e){
			throw new BusinessException("未找到对应的订单处理器:" + beanName);
		}
		return handler;
	}


	public static Order handle(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception {
		return getHandler(orderType).doHandle(action, orderType, orderDef);
	}

	@Autowired
	private OrderMapper orderMapper;

	public Order doHandle(OrderAction action,OrderType orderType,OrderModel orderModel) throws Exception{
		if(ComUtil.isEmpty(action) || ComUtil.isEmpty(orderType) || ComUtil.isEmpty(orderModel)
				|| ComUtil.isEmpty(orderModel.getOrderNo())){
			throw new BusinessException("数据验证失败");
		}
		Order order = new Order();
		order.setOrderNo(orderModel.getOrderNo());
		Order currentOrder = orderMapper.selectOne(order);
		if(ComUtil.isEmpty(currentOrder))
			throw new BusinessException("未查询到数据");
		OrderStatus nextStatus = orderType.getStatusHolder().getByAction(action);
		OrderStatus prevStatus = orderType.getStatusHolder().getByName(currentOrder.getOrderStatus());

		if(!prevStatus.canTransformTo(nextStatus))
			throw new BusinessException("订单不能由" + prevStatus.getName() + "转变为" + nextStatus.getName());
		orderModel.setOrderStatus(nextStatus.getName());
		return this.handleInternal(action,orderType,orderModel,currentOrder);
	}

	public abstract Order handleInternal(OrderAction action,OrderType orderType,OrderModel orderDef,Order currentOrder) throws Exception;


}