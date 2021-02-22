package com.hongye.service.impl;

import com.hongye.entity.Order;
import com.hongye.enums.OrderAction;
import com.hongye.enums.OrderType;
import com.hongye.mapper.OrderMapper;
import com.hongye.model.OrderModel;
import com.hongye.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongye.service.handler.OrderHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主表，当前表只保存流转中的订单信息 服务实现类
 * </p>
 *
 * @author hongye
 * @since 2018-10-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Override
    public Order handleOrder(OrderAction action, OrderType orderType, OrderModel orderDef) throws Exception {
        Order order = OrderHandler.handle(action, orderType, orderDef);
        return order;
    }

}
