package com.bing.constants;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/20
 * Time: 11:58
 * To change this template use File | Setting | File Template.
 **/
public enum LogEnum {
    BUSSINESS("bussiness"),

    PLATFORM("platform"),

    DB("db"),

    EXCEPTION("exception"),

            ;


    private String category;


    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
