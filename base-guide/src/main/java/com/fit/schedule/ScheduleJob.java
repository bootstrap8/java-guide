package com.fit.schedule;

import java.util.concurrent.*;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-11-05
 */
public class ScheduleJob {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    static class Job implements Runnable {

        @Override
        public void run() {
            System.out.println("do sth...");
        }
    }

    public static void main(String[] args) {
        //一分钟执行一次
        scheduledExecutorService.scheduleAtFixedRate(new Job(), 1000L, 60 * 1000L, TimeUnit.MILLISECONDS);
    }

}
