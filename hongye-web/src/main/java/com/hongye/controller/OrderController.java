package com.hongye.controller;

import com.hongye.config.ResponseHelper;
import com.hongye.config.ResponseModel;
import com.hongye.entity.Order;
import com.hongye.enums.OrderAction;
import com.hongye.enums.OrderType;
import com.hongye.model.OrderModel;
import com.hongye.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongye
 * @since 2018-10-17
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 测试工作流
     * 订单发货(动作),待发货-->已发货(状态)
     * @param orderType
     * @param orderModel
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/deliver/{orderType}")
    public ResponseModel<Order> updateDeliver(@PathVariable String orderType, @RequestBody OrderModel orderModel)
            throws Exception {
        Order orderDef = orderService.handleOrder(OrderAction.deliver, OrderType.getInstance(orderType), orderModel);
        return ResponseHelper.succeed(orderDef);
    }


}
