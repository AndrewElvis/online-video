package zust.xyt.userservice.service.impl;

import zust.xyt.userservice.entity.User;
import zust.xyt.userservice.entity.vo.ChannalVo;
import zust.xyt.userservice.mapper.UserMapper;
import zust.xyt.userservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EricWang
 * @since 2020-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
