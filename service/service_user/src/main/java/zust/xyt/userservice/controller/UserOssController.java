package zust.xyt.userservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zust.xyt.ResponseResult;
import zust.xyt.userservice.service.OssService;

/**
 * @author AndrewElvis
 * @date 2020-06-26-14:19
 * @description
 */
@RestController
@RequestMapping("/user/oss")
@CrossOrigin
public class UserOssController {
    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping("/upload")
    public ResponseResult uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return ResponseResult.ok().data("url",url);
    }
}
