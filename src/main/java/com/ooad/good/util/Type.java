package com.ooad.good.util;

/**
 * @author wz
 */

public enum Type {
    /**
     * SELECT(0), 查询
     * INSERT(1), 插入
     * UPDATE(2), 更新
     * DELETE(3),删除
     */
    SELECT(0), INSERT(1), UPDATE(2), DELETE(3);

    private Integer index;

    Type(Integer num) {
        this.index = num;
    }

    public Integer getIndex() {
        return index;
    }
}
