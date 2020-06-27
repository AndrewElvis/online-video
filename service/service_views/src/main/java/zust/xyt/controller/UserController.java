package zust.xyt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import zust.xyt.ResponseResult;
import zust.xyt.entity.vo.SubscribeVo;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author AndrewElvis
 * @date 2020-06-27-16:38
 * @description
 */
@Controller
@RequestMapping("/views")
public class UserController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String test(@PathVariable String id) {
        ResponseResult result = restTemplate.getForObject("http://SERVICE-USER/user/subscribe/" + id,
                ResponseResult.class);
        Map<String, Object> data = result.getData();
        ArrayList<SubscribeVo> rows = (ArrayList<SubscribeVo>) data.get("rows");
        rows.forEach(System.out::println);
        return "";
    }
}
