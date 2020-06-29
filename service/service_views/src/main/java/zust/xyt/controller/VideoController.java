package zust.xyt.controller;

import com.alibaba.fastjson.JSON;
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
import zust.xyt.entity.vo.SubscribeVo;
import zust.xyt.entity.vo.subVideoVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        ResponseResult res = restTemplate.getForObject("http://SERVICE-VIDEO/vod/getPlayUrl/" + id, ResponseResult.class);
        Video video = restTemplate.getForObject("http://SERVICE-VIDEO/video/getBySourceId/" + id, Video.class);
        List<LinkedHashMap> videoList = restTemplate.getForObject("http://SERVICE-VIDEO/video", List.class);

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
                    return (simpleDateFormat.parse(a.getGmtCreate())).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }));
        }

        for (LinkedHashMap v : videoList) {
            System.out.println(v.get("userId"));
            User user = restTemplate.getForObject("http://SERVICE-USER/user/" + v.get("userId"), User.class);
            v.put("userNickName", user.getNickname());
            v.put("userAvatar", user.getAvatar());
        }
        User me = restTemplate.getForObject("http://SERVICE-USER/user/" + "1273857412729692122", User.class);
        Video singleVideo = restTemplate.getForObject("http://SERVICE-VIDEO/video/getBySourceId/" + id, Video.class);
        User videoUser = restTemplate.getForObject("http://SERVICE-USER/user/" + singleVideo.getUserId(), User.class);
        model.addAttribute("user",me);
        model.addAttribute("url", res.getData().get("url"));
        model.addAttribute("video", video);
        model.addAttribute("videoList", videoList);
        model.addAttribute("videoUser", videoUser);
        return "single-video";
    }

    @GetMapping("/toChannals/{id}")
    public String channals(@PathVariable String id,Model model){
        ArrayList topChannals = restTemplate.getForObject("http://SERVICE-USER/user/topChannals", ArrayList.class);
        //bar
        ArrayList subs = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);
        User me = restTemplate.getForObject("http://SERVICE-USER/user/" + id, User.class);

        Iterator iterator = subs.iterator();
        List<subVideoVo> subVideos = new ArrayList<>();
        while (iterator.hasNext()){
            LinkedHashMap next = (LinkedHashMap) iterator.next();
            List<LinkedHashMap> subVideo = restTemplate.getForObject("http://SERVICE-VIDEO/video/getByUserId/" + next.get("id"), List.class);
            User subscribe = restTemplate.getForObject("http://SERVICE-USER/user/" + next.get("id"), User.class);
            for (LinkedHashMap video : subVideo) {
                subVideoVo subVideoVo = new subVideoVo();
                subVideoVo.setTitle((String) video.get("title"));
                subVideoVo.setSourceId((String) video.get("sourceId"));
                subVideoVo.setCount((String) video.get("count"));
                subVideoVo.setCover((String) video.get("cover"));
                subVideoVo.setGmtCreate((String) video.get("gmtCreate"));
                subVideoVo.setUserAvatar(subscribe.getAvatar());
                subVideoVo.setUserNickName(subscribe.getNickname());
                subVideos.add(subVideoVo);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        if (subVideos != null){
            subVideos.sort(Comparator.comparingLong(a -> {
                try {
                    return (simpleDateFormat.parse(a.getGmtCreate())).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }));
        }

        model.addAttribute("user",me);
        model.addAttribute("subscribes",subs);
        model.addAttribute("topChannals",topChannals);
        return "browse-channals";
    }


    @GetMapping("/toHome/{id}")
    public String home(@PathVariable String id, Model model) {
        List<LinkedHashMap> result = restTemplate.getForObject("http://SERVICE-VIDEO/video", List.class);
        //bar：关注者
        ArrayList subs = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);
        ArrayList findChannals = restTemplate.getForObject("http://SERVICE-USER/user/findChannals/" + id, ArrayList.class);
        //bar：个人信息
        User me = restTemplate.getForObject("http://SERVICE-USER/user/" + id, User.class);

        Iterator iterator = subs.iterator();
        List<subVideoVo> subVideos = new ArrayList<>();
        while (iterator.hasNext()){
            LinkedHashMap next = (LinkedHashMap) iterator.next();
            List<LinkedHashMap> subVideo = restTemplate.getForObject("http://SERVICE-VIDEO/video/getByUserId/" + next.get("id"), List.class);
            User subscribe = restTemplate.getForObject("http://SERVICE-USER/user/" + next.get("id"), User.class);
            for (LinkedHashMap video : subVideo) {
                subVideoVo subVideoVo = new subVideoVo();
                subVideoVo.setTitle((String) video.get("title"));
                subVideoVo.setSourceId((String) video.get("sourceId"));
                subVideoVo.setCount((String) video.get("count"));
                subVideoVo.setCover((String) video.get("cover"));
                subVideoVo.setGmtCreate((String) video.get("gmtCreate"));
                subVideoVo.setUserAvatar(subscribe.getAvatar());
                subVideoVo.setUserNickName(subscribe.getNickname());
                subVideos.add(subVideoVo);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        if (subVideos != null){
        subVideos.sort(Comparator.comparingLong(a -> {
            try {
                return (simpleDateFormat.parse(a.getGmtCreate())).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }));
        }

        model.addAttribute("subVideos",subVideos);
        model.addAttribute("user",me);
        model.addAttribute("findChannals",findChannals);
        model.addAttribute("subscribes",subs);
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
