package zust.xyt.exceptionhandler;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zust.xyt.ResponseResult;

/**
 * @author Er1cWang
 * @create 2020-06-19-14:03
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
        e.printStackTrace();
        return ResponseResult.error().message("执行了全局异常处理..");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseResult error(ArithmeticException e){
        e.printStackTrace();
        return ResponseResult.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(VlogException.class)
    @ResponseBody
    public ResponseResult error(VlogException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseResult.error().code(e.getCode()).message(e.getMsg());
    }
}
