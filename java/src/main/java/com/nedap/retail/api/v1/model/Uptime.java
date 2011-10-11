package com.nedap.retail.api.v1.model;

/**
 * @deprecated
 */
public class Uptime {

    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private Integer year;
    private Integer month;
    private Integer date;
    private Integer day;
    private Integer time;
    private Integer timezoneOffset;

    public Integer getHours() {
        return hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (year!=null) {
            result.append(year);
            result.append(" years ");
        }
        if (month!=null) {
            result.append(month);
            result.append(" months ");
        }
        if (day!=null) {
            result.append(day);
            result.append(" days ");
        }
        if (hours!=null) {
            result.append(hours);
            result.append(" hours ");
        }
        if (minutes!=null) {
            result.append(minutes);
            result.append(" minutes ");
        }
        if (seconds!=null) {
            result.append(seconds);
            result.append(" seconds ");
        }
        return result.toString();
    }
}
