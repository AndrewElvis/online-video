package zust.xyt.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Er1cWang
 * @create 2020-06-18-16:50
 */

@Data
public class UserQuery {
    @ApiModelProperty(value = "用户名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "用户性别")
    private int sex;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

    @ApiModelProperty(value = "最少粉丝数量")
    private int fansMinNum;

    @ApiModelProperty(value = "最少关注数量")
    private int subscribeMinNum;
}
