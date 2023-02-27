package org.sunso.statemachine.demo;

import org.sunso.statemachine.context.Context;

public class DemoContext implements Context {
    private String tag;
    private String mysqlData;
    private String redisData;

    public String getTag() {
        return tag;
    }

    public DemoContext setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getMysqlData() {
        return mysqlData;
    }

    public DemoContext setMysqlData(String mysqlData) {
        this.mysqlData = mysqlData;
        return this;
    }

    public String getRedisData() {
        return redisData;
    }

    public DemoContext setRedisData(String redisData) {
        this.redisData = redisData;
        return this;
    }

    public boolean isEmptyTag() {
        if (tag == null) {
            return true;
        }
        if (tag.trim().length() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DemoContext{" + "tag='" + tag + '\'' + ", mysqlData='" + mysqlData + '\'' + ", redisData='" + redisData
                + '\'' + '}';
    }
}
