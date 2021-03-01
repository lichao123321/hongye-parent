package com.hongye.service.impl;

import com.hongye.base.Constant;
import com.hongye.entity.User;
import com.hongye.entity.UserThirdparty;
import com.hongye.mapper.UserThirdpartyMapper;
import com.hongye.model.ThirdPartyUser;
import com.hongye.service.IUserService;
import com.hongye.service.IUserThirdpartyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方用户表 服务实现类
 * </p>
 *
 * @author hongye123
 */
@Service
public class UserThirdpartyServiceImpl extends ServiceImpl<UserThirdpartyMapper, UserThirdparty> implements IUserThirdpartyService {

    @Autowired
    private IUserService userService;

    @Override
    public User insertThirdPartyUser(ThirdPartyUser param, String password) throws Exception{
        User sysUser = User.builder().password(password).username("游客"+param.getOpenid()).mobile(param.getOpenid())
                .avatar(param.getAvatarUrl()).build();
        User register = userService.register(sysUser, Constant.RoleType.USER);
        // 初始化第三方信息
        UserThirdparty thirdparty = UserThirdparty.builder().providerType(param.getProvider()).openId(param.getOpenid()).createTime(System.currentTimeMillis())
                .userNo(register.getUserNo()).status(Constant.ENABLE).accessToken(param.getToken()).build();
        this.insert(thirdparty);
        return register;
    }
}
