package org.finalbubble.knows.portal.service.impl;

import org.finalbubble.knows.portal.model.Permission;
import org.finalbubble.knows.portal.mapper.PermissionMapper;
import org.finalbubble.knows.portal.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
