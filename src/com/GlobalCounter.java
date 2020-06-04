package com;

/**
 * Created by neetriht on 2017-06-06.
 */
public class GlobalCounter {

    public static Integer READ_SUM = 0, SCRAPY_SUM = 0, GLOBAL = 0, COUNTER_SAVED_GLOBAL = 0, COUNTER_SAVED_CHECK = 0, GLOBAL_REMOVE = 0, GLOBAL_MISSED = 0;
    public static Integer Fresher_Close_No = 0, Fresher_Insert_No = 0, Fresher_Update_No = 0;

    public static Integer HBASE_ADD = 0, HBASE_UPDATE = 0, HBASE_CHECK = 0;
    public static Integer REDIS_ADD = 0, REDIS_CHECK = 0;
    public static Integer Mongo_ADD = 0, Mongo_CHECK = 0;

    public static void iniCounters() {
        GlobalCounter.READ_SUM = 0;
        //* 读取的数据
        GlobalCounter.SCRAPY_SUM = 0;
        //* 有效的数据
        GlobalCounter.COUNTER_SAVED_GLOBAL = 0;
        GlobalCounter.COUNTER_SAVED_CHECK = 0;
        GlobalCounter.GLOBAL_REMOVE = 0;
        GlobalCounter.GLOBAL_MISSED = 0;
        GlobalCounter.GLOBAL = 0;
        GlobalCounter.Fresher_Close_No = 0;
        GlobalCounter.Fresher_Insert_No = 0;
        GlobalCounter.Fresher_Update_No = 0;
        //*
        //Ini HBASE counters
        GlobalCounter.HBASE_ADD = 0;
        GlobalCounter.HBASE_UPDATE = 0;
        GlobalCounter.HBASE_CHECK = 0;
        //Ini REDIS counters
        GlobalCounter.REDIS_ADD = 0;
        GlobalCounter.REDIS_CHECK = 0;
        //Ini MongoDB counters
        GlobalCounter.Mongo_ADD = 0;
        GlobalCounter.Mongo_CHECK = 0;
    }
}
