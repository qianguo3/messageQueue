package com.badu.delay;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTest {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Task> delayqueue = new DelayQueue<>();
        long now = System.currentTimeMillis()/1000;
        System.out.println("now:"+now);
        delayqueue.put(new Task(now + 3));
        delayqueue.put(new Task(now + 6));
        delayqueue.put(new Task(now + 9));
        delayqueue.put(new Task(now + 10));
        System.out.println(delayqueue);

        for (int i = 0; i < 4; i++) {
            System.out.println(delayqueue.take());
        }
    }

    static class Task implements Delayed {
        private long time;

        public Task(long time) {
            this.time = time;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.SECONDS) < o.getDelay(TimeUnit.SECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.SECONDS) > o.getDelay(TimeUnit.SECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(time - System.currentTimeMillis()/1000, TimeUnit.SECONDS);
            System.out.println("ct:"+convert);
            return convert;
        }

        @Override
        public String toString() {
            return "" + time;
        }
    }
}
