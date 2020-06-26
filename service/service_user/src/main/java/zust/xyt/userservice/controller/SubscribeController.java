package zust.xyt.userservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zust.xyt.ResponseResult;
import zust.xyt.userservice.entity.Subscribe;
import zust.xyt.userservice.entity.User;
import zust.xyt.userservice.entity.vo.SubscribeVo;
import zust.xyt.userservice.service.SubscribeService;
import zust.xyt.userservice.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EricWang
 * @since 2020-06-20
 */
@RestController
@RequestMapping("/user/subscribe")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private UserService userService;

    /**
     * 添加关注
     * @param subscribe
     * @return
     */
    @ApiOperation(value = "添加关注")
    @PostMapping
    public ResponseResult save(@ApiParam(name = "subscribe",value = "关注对象",required = true)@RequestBody Subscribe subscribe){
        boolean save = subscribeService.save(subscribe);
        String subscriberId = subscribe.getSubscriberId();
        String subscribeId = subscribe.getSubscribeId();
        if (save) {
            //关注者关注数+1
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id", subscriberId);
            User one = userService.getOne(wrapper1);
            int subscribeNum = one.getSubscribeNum();
            one.setSubscribeNum(++subscribeNum);
            userService.updateById(one);
            //被关注者粉丝数+1
            QueryWrapper<User> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id", subscribeId);
            User one1 = userService.getOne(wrapper2);
            int fansNum = one1.getFansNum();
            one1.setFansNum(++fansNum);
            userService.updateById(one1);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "取消关注")
    @DeleteMapping
    public ResponseResult removeById(@ApiParam(name = "subscribe",value = "关注对象",required = true)@RequestBody Subscribe subscribe){
        String subscriberId = subscribe.getSubscriberId();
        String subscribeId = subscribe.getSubscribeId();
        HashMap<String, Object> map = new HashMap<>();
        map.put("subscriber_id",subscriberId);
        map.put("subscribe_id",subscribeId);
        boolean remove = subscribeService.removeByMap(map);
        if (remove){
            //关注者关注数-1
            QueryWrapper<User> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",subscriberId);
            User one = userService.getOne(wrapper1);
            int subscribeNum = one.getSubscribeNum();
            one.setSubscribeNum(--subscribeNum);
            userService.updateById(one);
            //被关注者粉丝数-1
            QueryWrapper<User> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id",subscribeId);
            User one1 = userService.getOne(wrapper2);
            int fansNum = one1.getFansNum();
            one1.setFansNum(--fansNum);
            userService.updateById(one1);
        }
        return ResponseResult.ok();
    }

    /**
     *
     * @param id
     * @return 用户关注列表
     */
    @ApiOperation(value = "单个用户的关注列表")
    @GetMapping("{id}")
    public ResponseResult getById(@ApiParam(name = "id",value = "用户id",required = true)@PathVariable String id ){
        HashMap<String, Object> Map = new HashMap<>();
        Map.put("subscriber_id",id);
        Collection<Subscribe> subscribes = subscribeService.listByMap(Map);
        Iterator<Subscribe> iterator = subscribes.iterator();
        ArrayList<SubscribeVo> users = new ArrayList<>();
        while (iterator.hasNext()){
            Subscribe next = iterator.next();
            SubscribeVo subscribeVo = new SubscribeVo();
            String subscribeId = next.getSubscribeId();
            User user = userService.getById(subscribeId);
            subscribeVo.setName(user.getName());
            subscribeVo.setAvatar(user.getAvatar());
            subscribeVo.setNickname(user.getNickname());
            subscribeVo.setSex(user.getSex());
            subscribeVo.setSubscribeTime(next.getGmtCreate().toString());
            users.add(subscribeVo);
        }
        return ResponseResult.ok().data("rows",users);
    }
}

