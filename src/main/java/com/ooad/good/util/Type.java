package com.ooad.good.util;

public enum Type {
    SELECT(0), INSERT(1), UPDATE(2), DELETE(3);

    private Integer index;

    Type(Integer num) {
        this.index = num;
    }

    public Integer getIndex() {
        return index;
    }
}
