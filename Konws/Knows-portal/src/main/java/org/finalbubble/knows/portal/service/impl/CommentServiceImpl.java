package org.finalbubble.knows.portal.service.impl;

import org.finalbubble.knows.portal.model.Comment;
import org.finalbubble.knows.portal.mapper.CommentMapper;
import org.finalbubble.knows.portal.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
