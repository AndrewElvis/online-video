package zust.xyt.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Er1cWang
 * @create 2020-06-20-18:02
 */
@Data
public class SubscribeVo {

    @ApiModelProperty(value = "被关注者用户名")
    private String name;

    @ApiModelProperty(value = "被关注者性别")
    private int sex;

    @ApiModelProperty(value = "被关注者昵称")
    private String nickname;

    @ApiModelProperty(value = "关注时间")
    private String subscribeTime;

    @ApiModelProperty(value = "被关注者头像")
    private String avatar;
}
