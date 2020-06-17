package zust.xyt;

import io.swagger.models.auth.In;

import java.util.PrimitiveIterator;

/**
 * @author AndrewElvis
 * @date 2020-06-17-14:16
 * @description
 */
public enum ResultCode {
    SUCCESS(20000),
    ERROR(20001);

    Integer code;

    ResultCode(int code) {
        this.code = code;
    }
}
