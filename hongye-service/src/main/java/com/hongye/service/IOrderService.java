package com.hongye.service;

import com.hongye.entity.Order;
import com.baomidou.mybatisplus.service.IService;
import com.hongye.enums.OrderAction;
import com.hongye.enums.OrderType;
import com.hongye.model.OrderModel;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务类
 * </p>
 *
 * @author hongye123
 */
public interface IOrderService extends IService<Order> {

    Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception;

}
