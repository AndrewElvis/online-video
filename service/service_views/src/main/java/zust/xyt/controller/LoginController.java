package zust.xyt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author AndrewElvis
 * @date 2020-06-19-20:40
 * @description
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    //    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/views/video/toHome/1273855936364044290";
        } else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
