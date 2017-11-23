package com.ipin.service.rest.utils.mail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ApplyMialTimter {
	  /**
     * 计时器
     */

    private  Timer timer =new Timer();

    /**
     * 在某个时间点执行一次
     * @param task
     * @param date
     * @author 一线天色 天宇星辰 创建于 2011-1-2 下午04:29:29
     */
    public void doOnce(TimerTask task,Date date) {
       timer.schedule(task, date);
    }

    public static void main(String[] args) {

    	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 17);
		today.set(Calendar.MINUTE, 01);
		today.set(Calendar.SECOND, 0);
		 Date date = new Date();
		 date.setTime(today.getTime().getTime());
        new ApplyMialTimter().doOnce(new ApplyMailTask(), date);
     }
}
