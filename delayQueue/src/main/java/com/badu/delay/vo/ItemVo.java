package com.badu.delay.vo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ItemVo<T> implements Delayed {

    //订单过期时的时间,单位ms
    private long activeTime;
    //业务数据
    private T data;

    public ItemVo(long activeTime, T data) {
        this.activeTime = activeTime+System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(activeTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS);
        return d==0?0:(d>0?1:-1);
    }
}
