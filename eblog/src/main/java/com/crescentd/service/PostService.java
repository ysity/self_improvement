package com.crescentd.service;

import com.crescentd.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
public interface PostService extends IService<Post> {
     void initWeekRank();
}
