package com.geek.wheel.util;

import java.util.concurrent.TimeUnit;

/**
 * @DESCRIPTION: 时间单位转换器
 * @CLASSNAME: TimeUnitConverter
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 14:51
 */
public class TimeUnitConverter {

    public static TimeUnit convert(String timeUnit){
        TimeUnit result;
        switch (timeUnit){
            case "d":
                result = TimeUnit.DAYS;
                break;
            case "h":
                result =  TimeUnit.HOURS;
                break;
            case "m":
                result =  TimeUnit.MINUTES;
                break;
            case "s" :
                result =  TimeUnit.SECONDS;
                break;
            case "ms":
                result =  TimeUnit.MILLISECONDS;
                break;
            case "ns":
                result =  TimeUnit.MICROSECONDS;
                break;
            default:
                result = TimeUnit.SECONDS;
                break;
        }
        return result;
    }
}
