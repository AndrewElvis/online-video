package zust.xyt.video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import zust.xyt.ResponseResult;
import zust.xyt.exceptionhandler.VlogException;
import zust.xyt.video.entity.Video;
import zust.xyt.video.entity.vo.VideoQuery;
import zust.xyt.video.service.VideoService;
import zust.xyt.video.service.VodService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AndrewElvis
 * @since 2020-06-25
 */
@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private VodService vodService;

    //添加视频
    @PostMapping
    public ResponseResult addVideo(@RequestBody Video video) {
        videoService.save(video);
        return ResponseResult.ok();
    }

    @GetMapping
    public List<Video> getAllVideo() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        List<Video> videoList = videoService.list(wrapper);
        return videoList;
    }

    //根据ID查看视频字段信息
    @GetMapping("{id}")
    public ResponseResult getVideo(@PathVariable String id) {
        Video video = videoService.getById(id);
        return ResponseResult.ok().data("video", video);
    }

    @GetMapping("/getBySourceId/{id}")
    public Video getBySourceId(@PathVariable String id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("source_id", id);
        Video video = (Video) videoService.getObj(wrapper);
        return video;
    }

    //更新视频信息
    @ApiOperation(value = "根据ID修改视频信息")
    @PutMapping("/{id}")
    public ResponseResult updateById(
            @ApiParam(name = "id",value = "用户ID",required = true)
            @PathVariable String id,
            @ApiParam(name = "user",value = "用户对象",required = true)
            @RequestBody Video video){
        video.setId(id);
        videoService.updateById(video);
        return ResponseResult.ok();
    }

    //删除视频，删除对应阿里云视频
    @DeleteMapping("/{id}")
    public ResponseResult deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        Video video = videoService.getById(id);
        String videoSourceId = video.getSourceId();
        //判断是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            ResponseResult result = vodService.removeAliVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new VlogException(20001,"删除视频失败，熔断器...");
            }
        }
        //删除小节
        videoService.removeById(id);
        return ResponseResult.ok();
    }

    /**
     *根据条件分页查询
     */
    @PostMapping("pageVideoCondition/{current}/{limit}")
    public ResponseResult pageUserCondition(@PathVariable long current, @PathVariable long limit,
                                            @RequestBody(required = false) VideoQuery videoQuery) {
        //创建page对象
        Page<Video> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        String title = videoQuery.getTitle();
        String tags = videoQuery.getTags();
        String begin = videoQuery.getBegin();
        String end = videoQuery.getEnd();
        String status = videoQuery.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(tags)) {
            wrapper.like("tags", tags);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        videoService.page(page, wrapper);
        long total = page.getTotal();
        List<Video> records = page.getRecords();
        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    @GetMapping("/getByUserId/{id}")
    public List<Video> getVideoByUserId(@PathVariable String id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        List<Video> videoList = videoService.list(wrapper);
        return videoList;
    }

    @GetMapping("/getMostCountVideoByUserId/{id}")
    public Video getMostCountVideoByUserId(@PathVariable String id) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        wrapper.orderByDesc("count");
        wrapper.last("limit 1");
        return videoService.getOne(wrapper);
    }

}

