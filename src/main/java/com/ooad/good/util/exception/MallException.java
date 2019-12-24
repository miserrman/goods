package com.ooad.good.util.exception;

import com.ooad.good.domain.Brand;
import com.ooad.good.util.ResponseCode;
import com.ooad.good.util.ResponseUtil;

public class MallException extends Exception {
    private ResponseCode code = null;

    public MallException(ResponseCode code) {
        this.code = code;
    }

    public Object getErrorCode() {
        return ResponseUtil.fail(code.getCode(), null);
    }
}
