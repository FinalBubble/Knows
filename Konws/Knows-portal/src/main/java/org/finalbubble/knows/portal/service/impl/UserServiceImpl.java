package org.finalbubble.knows.portal.service.impl;

import org.finalbubble.knows.portal.model.User;
import org.finalbubble.knows.portal.mapper.UserMapper;
import org.finalbubble.knows.portal.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finalbubble.org
 * @since 2023-07-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
