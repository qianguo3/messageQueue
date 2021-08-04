package com.badu.service.impl;

import com.badu.delay.IDelayOrder;
import com.badu.mapper.OrderExpMapper;
import com.badu.model.OrderExp;
import com.badu.service.IOrderExpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class OrderExpImpl implements IOrderExpService {

    private Logger logger = LoggerFactory.getLogger(OrderExpImpl.class);

    @Resource
    private OrderExpMapper orderExpMapper;

    @Resource
    private IDelayOrder iDelayOrder;


    @Override
    public List<OrderExp> findAll() {
        List<OrderExp> all = orderExpMapper.findAll();
        return all;
    }

    @Override
    public void insert(int orderNumber) {
        Random r = new Random();
        OrderExp orderExp ;
        for(int i=0;i<orderNumber;i++) {
            //订单的超时时长，单位秒
            long expireTime = r.nextInt(20)+5;
            orderExp = new OrderExp();
            String orderNo = "DD00_"+expireTime+"S";
            orderExp.setOrderNo(orderNo);
            orderExp.setOrderNote("享学订单——"+orderNo);
            orderExp.setOrderStatus((short) 0);
            orderExpMapper.insertDelayOrder(orderExp,expireTime);
            logger.info("保存订单到DB:"+orderNo);
            iDelayOrder.orderDelay(orderExp,expireTime*1000);
        }

    }
}
