package zust.xyt.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Er1cWang
 * @create 2020-06-19-14:24
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor  //生成无参数构造方法
public class VlogException extends RuntimeException{

    private Integer code;

    private String msg;
}
