package com.ooad.good.util;

public enum ResponseCode {
    ADD_GOODS_ERROR(771),
    DELETE_GOODS_ERROR(772),
    UPDATE_GOODS_ERROR(773),
    UNDER_SHELF_ERROR(774),
    GOODS_UNKNOWN(775),
    GET_GOODS_LIST_FAIL(776),

    PRODUCT_ADD_ERROR(781),
    PRODUCT_UPDATE_ERROR(782),
    PRODUCT_DELETE_ERROR(783),
    PRODUCT_UNKNOWN(784),
    GET_PRODUCT_LIST_FAIL(785),
    GET_PRODUCT_STOCK_FAIL(786),

    BRAND_ADD_ERROR(791),
    BRAND_UPDATE_ERROR(792),
    BRAND_DELETE_ERROR(793),
    BRAND_UNKNOWN(794),
    GET_BRAND_LIST_FAIL(795),

    GOODSCATEGORY_ADD_ERROR(801),
    GOODSCATEGORY_UPDATE_ERROR(802),
    GOODSCATEGORY_DELETE_ERROR(803),
    GOODSCATEGORY_UNKNOWN(804),
    GET_GOODSCATEGORY_LIST_FAIL(805),
    BAD_ARGUMENT(580);

    private Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    Object error(ResponseCode code) {
        return ResponseUtil.fail(code.getCode(), null);
    }
}
