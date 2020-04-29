package com.crescentd.service.impl;

import com.crescentd.entity.Comment;
import com.crescentd.mapper.CommentMapper;
import com.crescentd.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
