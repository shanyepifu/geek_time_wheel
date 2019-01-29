package com.geek.wheel;

import com.geek.wheel.entity.Task;
import com.geek.wheel.listener.CountCycleListener;
import com.geek.wheel.listener.ExcuteTaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeWheel {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 时间轮tick数
     */
    private Integer tickDuration = 1;

    /**
     * 时间轮tick间隔时间
     */
    private TimeUnit tickDurationTimeUnit = TimeUnit.SECONDS;

    /**
     * 时间轮tick间隔时间单位
     */
    private Integer ticksPerWheel = 60;

    /**
     * 指针维护线程
     */
    private Thread worker;

    /**
     * 时间轮盘
     */
    private List<Vector<Task>> wheel;

    /**
     * 是否关闭
     */
    private AtomicBoolean shutdown = new AtomicBoolean(false);

    /**
     * 当前指针位置
     */
    private Integer currentPoint = 0;

    /**
     * 计算圈数的监听器
     */
    private CountCycleListener countCycleListener;

    /**
     * 任务执行的监听器
     */
    private ExcuteTaskListener excuteTaskListener;

    /**
     * 构造时间轮
     * @param ticksPerWheel         时间轮tick数
     * @param tickDuration          时间轮tick间隔时间
     * @param tickDurationTimeUnit  时间轮tick间隔时间单位
     */
    public TimeWheel(Integer ticksPerWheel, Integer tickDuration, TimeUnit tickDurationTimeUnit) {
        this.tickDuration = tickDuration;
        this.tickDurationTimeUnit = tickDurationTimeUnit;
        this.ticksPerWheel = ticksPerWheel;
    }

    public void initTimingWheel(){
        wheel = new ArrayList<>(getTicksPerWheel());
        for (int i = 0; !shutdown.get(); i++){
            // 循环完成
            if (i == getTicksPerWheel()){
                i = 0;
                countCycleListener.countCycle();
            }
            currentPoint = i;
            excuteTaskListener.excute(currentPoint);
            try {
                long milliseDuration = TimeUnit.MILLISECONDS.convert(tickDuration, tickDurationTimeUnit);
                Thread.sleep(milliseDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.debug("Time wheel started now...");
    }

    public void start(){
        worker = new Thread(() -> initTimingWheel() );
        worker.start();
    }


    public void shuttdown(){
        if (shutdown.get()){
            logger.debug("Time wheel is already shutdown...");
        } else {
            shutdown.set(true);
            logger.debug("Time wheel is shutdown...");
        }
    }

    // Getter And Setter

    public void setCountCycleListener(CountCycleListener countCycleListener) {
        this.countCycleListener = countCycleListener;
    }

    public void setExcuteTaskListener(ExcuteTaskListener excuteTaskListener) {
        this.excuteTaskListener = excuteTaskListener;
    }

    public Integer getTickDuration() {
        return tickDuration;
    }

    public void setTickDuration(Integer tickDuration) {
        this.tickDuration = tickDuration;
    }

    public TimeUnit getTickDurationTimeUnit() {
        return tickDurationTimeUnit;
    }

    public void setTickDurationTimeUnit(TimeUnit tickDurationTimeUnit) {
        this.tickDurationTimeUnit = tickDurationTimeUnit;
    }

    public Integer getTicksPerWheel() {
        return ticksPerWheel;
    }

    public void setTicksPerWheel(Integer ticksPerWheel) {
        this.ticksPerWheel = ticksPerWheel;
    }
}
