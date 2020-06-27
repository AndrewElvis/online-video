package zust.xyt.video.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zust.xyt.ResponseResult;
import zust.xyt.exceptionhandler.VlogException;
import zust.xyt.video.service.VodService;
import zust.xyt.video.utils.ConstantVodUtils;
import zust.xyt.video.utils.InitVodClient;

import javax.ws.rs.Path;
import java.util.List;

/**
 * @author AndrewElvis
 * @date 2020-06-25-21:58
 * @description
 */
@RestController
@RequestMapping("/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliVideo")
    public ResponseResult uploadAliVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAli(file);
        return ResponseResult.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAliVideo/{id}")
    public ResponseResult removeAliVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return ResponseResult.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new VlogException(20001,"删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("deleteBatch")
    public ResponseResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAliVideo(videoIdList);
        return ResponseResult.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public ResponseResult getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return ResponseResult.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new VlogException(20001,"获取凭证失败");
        }
    }

    @GetMapping("/getPlayUrl/{id}")
    public ResponseResult getPlayUrl(@PathVariable("id") String id) {
        String playUrl = vodService.getPlayUrl(id);
        return ResponseResult.ok().data("url", playUrl);
    }
}
