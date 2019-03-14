package com.common;

/**
 * User: han.xiong
 * Date: 13-9-12
 * Time: 下午5:28
 */
public class TimeCounter {

    private long startTime = 0L;

    private long lastTick = 0L;

    /**
     * 开始计时
     * @return 当前微秒数
     */
    public long start() {
        startTime = System.currentTimeMillis();
        lastTick = startTime;
        return startTime;
    }

    /**
     * 返回距离上一次 tick 的毫秒数
     * @return
     */
    public long tick() {
        long curTick = System.currentTimeMillis();
        long timeCnt;
        if (lastTick == 0L) {
            timeCnt = curTick - startTime;
        } else {
            timeCnt = curTick - lastTick;
        }
        lastTick = curTick;
        return timeCnt;
    }

    /**
     * 返回开始计时到现在的毫秒数
     * @return
     */
    public long timeCount() {
        return System.currentTimeMillis() - startTime;
    }

}
