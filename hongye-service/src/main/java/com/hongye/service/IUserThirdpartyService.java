package com.hongye.service;

import com.hongye.entity.User;
import com.hongye.entity.UserThirdparty;
import com.baomidou.mybatisplus.service.IService;
import com.hongye.model.ThirdPartyUser;

/**
 * <p>
 * 第三方用户表 服务类
 * </p>
 *
 * @author hongye123
 */
public interface IUserThirdpartyService extends IService<UserThirdparty> {

    User insertThirdPartyUser(ThirdPartyUser param, String password)throws Exception;

}
