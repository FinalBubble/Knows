package org.finalbubble.knows.portal.service.impl;

import org.finalbubble.knows.portal.model.Tag;
import org.finalbubble.knows.portal.mapper.TagMapper;
import org.finalbubble.knows.portal.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
