package com.yinshan.happycash.analytic.location;

/**
 * Created by yyhuang on 2017/7/8  14:10.
 */

public class LocationRetryPolicy {
    private int mCurrentRetryCount;
    private final int mMaxNumRetries;
    public static final int DEFAULT_MAX_RETRIES = 3;

    public LocationRetryPolicy() {
        this(DEFAULT_MAX_RETRIES);
    }

    public LocationRetryPolicy(int maxNumRetries) {
        this.mMaxNumRetries = maxNumRetries;
    }

    public void retry() {
        ++mCurrentRetryCount;
    }

    public void reset() {
        mCurrentRetryCount = 0;
    }

    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    public boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
