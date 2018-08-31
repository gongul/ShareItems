package com.donggun.shareitems.module;

public enum ShareTeamAccessEnum{
    ALLOW_COMMAND(new String[]{"set","add"});

    private String[] str;

    ShareTeamAccessEnum(String[] str){
        this.str = str;
    }

    public String[] getStr() {
        return str;
    }
}