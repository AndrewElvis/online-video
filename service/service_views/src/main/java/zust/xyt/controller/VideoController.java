package zust.xyt.controller;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import zust.xyt.ResponseResult;
import zust.xyt.entity.User;
import zust.xyt.entity.Video;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author AndrewElvis
 * @date 2020-06-27-18:48
 * @description
 */
@Controller
@RequestMapping("/views/video")
public class VideoController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String singleVideo(@PathVariable String id, Model model) {
        ResponseResult res = restTemplate.getForObject("http://SERVICE-VIDEO/vod/getPlayUrl/" + id,
                ResponseResult.class);
        model.addAttribute("url", res.getData().get("url"));
        return "single-video";
    }

    @GetMapping("/toHome")
    public String home(Model model) {
        List<LinkedHashMap> result = restTemplate.getForObject("http://SERVICE-VIDEO/video", List.class);

        for (LinkedHashMap video : result) {
            System.out.println(video.get("userId"));
            User user = restTemplate.getForObject("http://SERVICE-USER/user/" + video.get("userId"), User.class);
            video.put("userNickName", user.getNickname());
            video.put("userAvatar", user.getAvatar());
        }
        model.addAttribute("videoList", result);

        return "home";
    }
}
