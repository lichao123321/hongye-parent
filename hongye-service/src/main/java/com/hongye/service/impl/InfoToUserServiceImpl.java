package com.hongye.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hongye.entity.InfoToUser;
import com.hongye.mapper.InfoToUserMapper;
import com.hongye.service.IInfoToUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户电话关系表 服务实现类
 * </p>
 *
 * @author hongye123
 */
@Service
public class InfoToUserServiceImpl extends ServiceImpl<InfoToUserMapper, InfoToUser> implements IInfoToUserService {

}
