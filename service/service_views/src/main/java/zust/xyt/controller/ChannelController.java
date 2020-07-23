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
import zust.xyt.entity.vo.subVideoVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String toSingleChannel(@PathVariable String id, Model model, String userId) {
        User user = restTemplate.getForObject("http://SERVICE-USER/user/" + id, User.class);
        List<LinkedHashMap> videoList = restTemplate.getForObject("http://SERVICE-VIDEO/video/getByUserId/" + id, List.class);
        Video video = restTemplate.getForObject("http://SERVICE-VIDEO/video/getMostCountVideoByUserId/" + id,
                Video.class);
        User me = restTemplate.getForObject("http://SERVICE-USER/user/" + userId, User.class);
        ArrayList users = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);
        ResponseResult res = restTemplate.getForObject("http://SERVICE-VIDEO/vod/getPlayUrl/" + video.getSourceId(),
                ResponseResult.class);
        for (LinkedHashMap v : videoList) {
            System.out.println(v.get("userId"));
            User u = restTemplate.getForObject("http://SERVICE-USER/user/" + v.get("userId"), User.class);
            v.put("userNickName", u.getNickname());
            v.put("userAvatar", u.getAvatar());
        }
        ArrayList subs = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);

        Iterator iterator = subs.iterator();
        List<subVideoVo> subVideos = new ArrayList<>();
        while (iterator.hasNext()){
            LinkedHashMap next = (LinkedHashMap) iterator.next();
            List<LinkedHashMap> subVideo = restTemplate.getForObject("http://SERVICE-VIDEO/video/getByUserId/" + next.get("id"), List.class);
            User subscribe = restTemplate.getForObject("http://SERVICE-USER/user/" + next.get("id"), User.class);
            for (LinkedHashMap subvideo : subVideo) {
                subVideoVo subVideoVo = new subVideoVo();
                subVideoVo.setTitle((String) subvideo.get("title"));
                subVideoVo.setSourceId((String) subvideo.get("sourceId"));
                subVideoVo.setCount((String) subvideo.get("count"));
                subVideoVo.setCover((String) subvideo.get("cover"));
                subVideoVo.setGmtCreate((String) subvideo.get("gmtCreate"));
                subVideoVo.setUserAvatar(subscribe.getAvatar());
                subVideoVo.setUserNickName(subscribe.getNickname());
                subVideos.add(subVideoVo);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        if (subVideos != null){
            subVideos.sort(Comparator.comparingLong(a -> {
                try {
                    return -((simpleDateFormat.parse(a.getGmtCreate())).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }));
        }
        /*try {
            simpleDateFormat.parse(user.getGmtCreate().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        model.addAttribute("subVideos",subVideos);

        model.addAttribute("subscribes",users);
        model.addAttribute("user",me);
        model.addAttribute("channelUser", user);
        model.addAttribute("channelVideoList", videoList);
        model.addAttribute("channelVideo", video);
        model.addAttribute("url", res.getData().get("url"));
        return "single-channal";
    }
}
