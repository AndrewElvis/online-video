package zust.xyt.userservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author AndrewElvis
 * @date 2020-06-26-14:21
 * @description
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
