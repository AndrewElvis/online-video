package zust.xyt.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zust.xyt.userservice.entity.User;
import zust.xyt.userservice.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EricWang
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/userservice/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     */
    @GetMapping("findAll")
    public List<User> findAllUser(){
        List<User> list = userService.list(null);
        return list;
    }

    /**
     * 删除用户
     */
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return userService.removeById(id);
    }
}

