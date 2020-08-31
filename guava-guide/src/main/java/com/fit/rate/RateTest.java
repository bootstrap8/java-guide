package com.fit.rate;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-07-01
 */
public class RateTest {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(4);

        for (int i = 1; i < 10; i = i + 2) {
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }
    }
}
