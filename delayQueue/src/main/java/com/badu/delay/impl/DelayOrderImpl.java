package com.badu.delay.impl;

import com.badu.delay.IDelayOrder;
import com.badu.delay.vo.ItemVo;
import com.badu.mapper.OrderExpMapper;
import com.badu.model.OrderExp;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

@Service
public class DelayOrderImpl implements IDelayOrder {



    private static DelayQueue<ItemVo<OrderExp>> delayQueue = new DelayQueue<ItemVo<OrderExp>>();

    @Resource
    private OrderExpMapper orderExpMapper;

    @Override
    public void orderDelay(OrderExp order, long expireTime) {
        ItemVo<OrderExp> orderExpItemVo = new ItemVo<OrderExp>(expireTime,order);
        delayQueue.put(orderExpItemVo);
    }


    class TakeOrder implements Runnable{
        @Override
        public void run() {
            while (true){

                try {
                    ItemVo<OrderExp> itemOrder = delayQueue.take();
                    System.out.println(itemOrder.getData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PostConstruct
    public void init(){
        new Thread(new TakeOrder()).start();
    }

}
