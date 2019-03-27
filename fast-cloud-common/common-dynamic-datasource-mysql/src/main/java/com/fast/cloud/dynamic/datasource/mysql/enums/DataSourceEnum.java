package com.fast.cloud.dynamic.datasource.mysql.enums;

public enum DataSourceEnum {

    DB1("slave1"),DB2("slave2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
