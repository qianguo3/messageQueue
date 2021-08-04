package com.badu.delay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author guoqian
 */
public class DelayTask implements Delayed {

    private String name;
    /**
     * 延迟时间
     */
    private Long delayTime;
    private TimeUnit timeUnit;
    /**
     * 过期时间
     */
    private Long expireTime;

    public DelayTask(String name, Long delayTime, TimeUnit timeUnit) {
        this.name = name;
        this.delayTime = delayTime;
        this.timeUnit = timeUnit;
        this.expireTime = delayTime + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expireTime - System.currentTimeMillis() / 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.MICROSECONDS) - o.getDelay(TimeUnit.MICROSECONDS);
        return d == 0 ? 0 : (d > 0 ? 1 : -1);
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "name='" + name + '\'' +
                ", delayTime=" + delayTime +
                ", timeUnit=" + timeUnit +
                ", expireTime=" + expireTime +
                '}';
    }


}
