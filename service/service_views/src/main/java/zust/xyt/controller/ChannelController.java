package zust.xyt.controller;

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
 * @date 2020-06-29-16:33
 * @description
 */
@Controller
@RequestMapping("/views/channel")
public class ChannelController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String toSingleChannel(@PathVariable String id, Model model) {
        User user = restTemplate.getForObject("http://SERVICE-USER/user/" + id, User.class);
        List<LinkedHashMap> videoList = restTemplate.getForObject("http://SERVICE-VIDEO/video/getByUserId/" + id, List.class);
        Video video = restTemplate.getForObject("http://SERVICE-VIDEO/video/getMostCountVideoByUserId/" + id,
                Video.class);
        User me = restTemplate.getForObject("http://SERVICE-USER/user/" + id, User.class);
        ArrayList users = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);
        ResponseResult res = restTemplate.getForObject("http://SERVICE-VIDEO/vod/getPlayUrl/" + video.getSourceId(),
                ResponseResult.class);
        for (LinkedHashMap v : videoList) {
            System.out.println(v.get("userId"));
            User u = restTemplate.getForObject("http://SERVICE-USER/user/" + v.get("userId"), User.class);
            v.put("userNickName", u.getNickname());
            v.put("userAvatar", u.getAvatar());
        }
        model.addAttribute("subscribes",users);
        model.addAttribute("user",me);
        model.addAttribute("channelUser", user);
        model.addAttribute("channelVideoList", videoList);
        model.addAttribute("channelVideo", video);
        model.addAttribute("url", res.getData().get("url"));
        return "single-channal";
    }
}
