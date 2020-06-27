package zust.xyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import zust.xyt.ResponseResult;
import zust.xyt.entity.vo.SubscribeVo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author AndrewElvis
 * @date 2020-06-27-16:38
 * @description
 */
@Controller
@RequestMapping("/views/user")
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String test(@PathVariable String id, Model model) {
        ArrayList users = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id, ArrayList.class);
        model.addAttribute("subscribes",users);
        return "home";
    }
}
