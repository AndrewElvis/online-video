package zust.xyt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AndrewElvis
 * @date 2020-06-17-14:15
 * @description
 */
@Data
public class ResponseResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private ResponseResult() {}

    //成功静态方法
    public static ResponseResult ok() {
        ResponseResult r = new ResponseResult();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS.code);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResponseResult error() {
        ResponseResult r = new ResponseResult();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR.code);
        r.setMessage("失败");
        return r;
    }

    public ResponseResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
