package zust.xyt.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author AndrewElvis
 * @since 2020-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Video对象", description="")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "分类类别ID")
    private String categoryId;

    @ApiModelProperty(value = "上传用户ID")
    private String userId;

    @ApiModelProperty(value = "视频标题")
    private String title;

    @ApiModelProperty(value = "视频简介")
    private String intro;

    @ApiModelProperty(value = "云端视频资源")
    private String sourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String originalName;

    @ApiModelProperty(value = "播放次数")
    private Long count;

    @ApiModelProperty(value = "点赞数")
    private Long likes;

    @ApiModelProperty(value = "视频时长(秒)")
    private Float duration;

    @ApiModelProperty(value = "视频标签")
    private String tags;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中 Normal正常")
    private String status;

    @ApiModelProperty(value = "视频原文件大小")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
