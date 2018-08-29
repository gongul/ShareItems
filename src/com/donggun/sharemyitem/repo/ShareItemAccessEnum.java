package com.donggun.sharemyitem.repo;

public enum ShareItemAccessEnum {
    ALLOW_COMMAND(new String[]{"all","users","team"});

    private String[] str;

    ShareItemAccessEnum(String[] str){
        this.str = str;
    }

    public String[] getStr() {
        return str;
    }
}
