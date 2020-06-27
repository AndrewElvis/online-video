package zust.xyt.userservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import zust.xyt.ResponseResult;
import zust.xyt.exceptionhandler.VlogException;
import zust.xyt.userservice.entity.User;
import zust.xyt.userservice.entity.vo.UserQuery;
import zust.xyt.userservice.service.UserService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author EricWang
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     */
    @ApiOperation(value = "用户列表")
    @GetMapping("findAll")
    public ResponseResult findAllUser() {
        List<User> list = userService.list(null);
        return ResponseResult.ok().data("items", list);
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户")
    @DeleteMapping("{id}")
    public ResponseResult removeById(@PathVariable String id) {
        boolean flag = userService.removeById(id);
        if (flag) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 分页
     *
     * @param current
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页用户列表")
    @GetMapping("pageUser/{current}/{limit}")
    public ResponseResult pageList(@ApiParam(name = "current", value = "当前页码", required = true)
                                   @PathVariable Long current,

                                   @ApiParam(name = "limit", value = "每页记录数", required = true)
                                   @PathVariable Long limit) {

        Page<User> pageParam = new Page<>(current, limit);
        try {
            int i = 10/0;
        } catch (Exception e) {
            //执行自定义异常
            throw new VlogException(20001,"执行了自定义异常处理...");
        }
        userService.page(pageParam, null);
        List<User> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    /**
     * 用户分页查询
     * @param current
     * @param limit
     * @param userQuery
     * @return
     */
    @PostMapping("pageUserCondition/{current}/{limit}")
    public ResponseResult pageUserCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) UserQuery userQuery) {
        //创建page对象
        Page<User> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String name = userQuery.getName();
        Integer sex = userQuery.getSex();
        String nickname = userQuery.getNickname();
        String begin = userQuery.getBegin();
        String end = userQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(sex)) {
            wrapper.eq("sex", sex);
        }
        if (!StringUtils.isEmpty(nickname)) {
            wrapper.like("nickname", nickname);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        userService.page(page, wrapper);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    /**
     * 添加用户
     */
    @ApiOperation(value = "新增用户")
    @PostMapping
    public ResponseResult save(@ApiParam(name = "user",value = "用户对象",required = true) @RequestBody User user) {
        userService.save(user);
        return ResponseResult.ok();
    }

    /**
     * 根据ID查询
     */
    @ApiOperation(value = "根据用户ID查询用户")
    @GetMapping("{id}")
    public ResponseResult getById(@ApiParam(name = "id",value = "讲师",required = true) @PathVariable String id){
        User user = userService.getById(id);
        return ResponseResult.ok().data("item",user);
    }

    /**
     * 根据ID修改
     */
    @ApiOperation(value = "根据ID修改用户")
    @PutMapping("{id}")
    public ResponseResult updateById(
            @ApiParam(name = "id",value = "用户ID",required = true)
            @PathVariable String id,
            @ApiParam(name = "user",value = "用户对象",required = true)
            @RequestBody User user){
        user.setId(id);
        userService.updateById(user);
        return ResponseResult.ok();
    }

    @PostMapping("/login")
    public ResponseResult login() {

        return ResponseResult.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.ok().data("roles", "[admin]").data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        return ResponseResult.ok();
    }
}