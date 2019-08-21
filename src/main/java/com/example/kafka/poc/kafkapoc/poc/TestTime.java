package com.example.kafka.poc.kafkapoc.poc;

import java.sql.Time;
import java.util.Calendar;

public class TestTime {

    public static void main(String[] args) {

        Calendar toDateDom = Calendar.getInstance();
        toDateDom.add(Calendar.MINUTE, 360);
        toDateDom.set(Calendar.MILLISECOND, 0);
        toDateDom.set(Calendar.SECOND, 0);
        toDateDom.set(Calendar.MINUTE, 0);


        Time sqlToTime = Time.valueOf(toDateDom.get(Calendar.HOUR_OF_DAY) + ":" + toDateDom.get(Calendar.MINUTE) + ":"
                + toDateDom.get(Calendar.SECOND));

        System.out.println(sqlToTime);
    }
}
