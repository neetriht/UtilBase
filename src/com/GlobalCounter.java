package com;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by neetriht on 2017-06-06.
 */
public class GlobalCounter {

    public static AtomicInteger READ_SUM = new AtomicInteger(0);
    public static AtomicInteger SCRAPY_SUM = new AtomicInteger(0);
    public static AtomicInteger GLOBAL = new AtomicInteger(0);
    public static AtomicInteger COUNTER_SAVED_GLOBAL = new AtomicInteger(0);
    public static AtomicInteger COUNTER_SAVED_CHECK = new AtomicInteger(0);
    public static AtomicInteger GLOBAL_REMOVE = new AtomicInteger(0);
    public static AtomicInteger GLOBAL_MISSED = new AtomicInteger(0);

    public static AtomicInteger Fresher_Close_No = new AtomicInteger(0);
    //public static Integer SCRAPY_SUM = 0, GLOBAL = 0, COUNTER_SAVED_GLOBAL = 0, COUNTER_SAVED_CHECK = 0, GLOBAL_REMOVE = 0, GLOBAL_MISSED = 0;
    public static AtomicInteger Fresher_Insert_No = new AtomicInteger(0);
    public static AtomicInteger Fresher_Update_No = new AtomicInteger(0);
    public static AtomicInteger HBASE_ADD = new AtomicInteger(0);
    public static AtomicInteger HBASE_UPDATE = new AtomicInteger(0);
    public static AtomicInteger HBASE_CHECK = new AtomicInteger(0);
    public static AtomicInteger REDIS_ADD = new AtomicInteger(0);
    public static AtomicInteger REDIS_CHECK = new AtomicInteger(0);
    public static AtomicInteger Mongo_ADD = new AtomicInteger(0);
    public static AtomicInteger Mongo_CHECK = new AtomicInteger(0);


    public static void iniCounters() {
        GlobalCounter.READ_SUM.set(0);
        //* 读取的数据
        GlobalCounter.SCRAPY_SUM.set(0);
        //* 有效的数据
        GlobalCounter.COUNTER_SAVED_GLOBAL.set(0);
        GlobalCounter.COUNTER_SAVED_CHECK.set(0);
        GlobalCounter.GLOBAL_REMOVE.set(0);
        GlobalCounter.GLOBAL_MISSED.set(0);
        GlobalCounter.GLOBAL.set(0);
        GlobalCounter.Fresher_Close_No.set(0);
        GlobalCounter.Fresher_Insert_No.set(0);
        GlobalCounter.Fresher_Update_No.set(0);
        //*
        //Ini HBASE counters
        GlobalCounter.HBASE_ADD.set(0);
        GlobalCounter.HBASE_UPDATE.set(0);
        GlobalCounter.HBASE_CHECK.set(0);
        //Ini REDIS counters
        GlobalCounter.REDIS_ADD.set(0);
        GlobalCounter.REDIS_CHECK.set(0);
        //Ini MongoDB counters
        GlobalCounter.Mongo_ADD.set(0);
        GlobalCounter.Mongo_CHECK.set(0);
    }
}
