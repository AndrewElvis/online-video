package zust.xyt.video.service;

import org.springframework.web.multipart.MultipartFile;
import zust.xyt.ResponseResult;

import java.util.List;

/**
 * @author AndrewElvis
 * @date 2020-06-25-21:40
 * @description
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAli(MultipartFile file);

    //删除多个阿里云视频的方法
    void removeMoreAliVideo(List videoIdList);

    ResponseResult removeAliVideo(String id);

    String getPlayUrl(String id);
}
