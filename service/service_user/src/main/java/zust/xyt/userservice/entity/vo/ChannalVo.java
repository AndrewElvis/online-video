package zust.xyt.userservice.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author Er1cWang
 * @create 2020-06-27-23:17
 */
@Data
public class ChannalVo {
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户名字")
    private String name;

    @ApiModelProperty("用户粉丝数")
    private Integer fansNum;
}
