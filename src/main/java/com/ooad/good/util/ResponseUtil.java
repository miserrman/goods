package com.ooad.good.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wz
 */
public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>(16);
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>(16);
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>(16);
        obj.put("errno", -1);
        obj.put("errmsg", "错误");
        return obj;
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>(16);
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object error(ResponseCode responseCode) {return fail(responseCode.getCode(), null);}

    public static Object badArgument() {
        return fail(580, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }

    public static Object brandUnknown() {return fail(320, "品牌不存在");}

    public static Object brandOperationFail() {return fail(321, "品牌操作失败");}

    public static Object goodsCategoryUnknown() {return fail(330, "商品分类不存在");}

    public static Object goodsCategoryOperationFail() {
        return fail(331, "商品分类操作失败");
    }

    public static Object goodsUnknown() {
        return fail(310, "商品不存在");
    }

    public static Object goodsOperationFail() {
        return fail(311, "商品操作失败");
    }

}
