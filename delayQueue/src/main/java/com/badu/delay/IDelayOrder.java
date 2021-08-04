package com.badu.delay;

import com.badu.model.OrderExp;

public interface IDelayOrder {

    public void orderDelay(OrderExp order, long expireTime);
}
