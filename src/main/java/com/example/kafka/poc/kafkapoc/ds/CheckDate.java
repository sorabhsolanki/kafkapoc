package com.example.kafka.poc.kafkapoc.ds;

import java.sql.Time;
import java.util.Calendar;

public class CheckDate {

    public static void main(String[] args) {
        Calendar toDateDom = Calendar.getInstance();
        Time sqlToTime;



        toDateDom.add(Calendar.HOUR_OF_DAY, 0);
        toDateDom.add(Calendar.MINUTE, 120);
        toDateDom.set(Calendar.MILLISECOND, 0);
        toDateDom.set(Calendar.SECOND, 0);

        sqlToTime = Time.valueOf(toDateDom.get(Calendar.HOUR_OF_DAY) + ":" + toDateDom.get(Calendar.MINUTE) + ":"
                + toDateDom.get(Calendar.SECOND));

        System.out.println(sqlToTime);
    }
}
