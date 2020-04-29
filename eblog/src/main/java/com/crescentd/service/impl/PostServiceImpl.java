package com.crescentd.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crescentd.entity.Post;
import com.crescentd.mapper.PostMapper;
import com.crescentd.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crescentd.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void initWeekRank() {
        List<Post> last7DayPosts  = this.list(new QueryWrapper<Post>()
                .ge("created", DateUtil.offsetDay(new Date(), -7).toJdkDate())
                .select("id, title, user_id, comment_count, view_count, created"));
        for (Post post:last7DayPosts ){
            String key = "day_rank:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_FORMAT);
            //设置有效期
            long between = DateUtil.between(new Date(),post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60;
            //缓存文章到set中，评论数量作为排行标准
            redisUtil.zSet(key,post.getId(),post.getCommentCount());
            //设置有效期
            redisUtil.expire(key,expireTime);
            //缓存文章基本信息（hash结构）
            this.hashCachePostIdAndTitle(post);
        }
        this.zUnionAndStoreLast7DaysForLastWeekRank();
    }

    /**
     * hash结构缓存文章标题和id
     * @param post
     */
    private void hashCachePostIdAndTitle(Post post){
        boolean isExit = redisUtil.hasKey("rank_post_"+post.getId());
        if (!isExit){
            //设置有效期
            long between = DateUtil.between(new Date(),post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60;
            //缓存文章基本信息（hash结构）
            redisUtil.hset("rank_post_"+post.getId(),"post:id",post.getId(),expireTime);
            redisUtil.hset("rank_post_"+post.getId(),"post:title",post.getTitle(),expireTime);
        }

    }

    /**
     * 把最近7天的文章评论数量统计一下
     * 用于首页的7天评论排行榜
     */
    private void zUnionAndStoreLast7DaysForLastWeekRank(){
        String prifix = "day_rank:";
        List<String> keys = new ArrayList<>();
        String key = prifix + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        for (int i = -7; i < 0; i++) {
            Date date = DateUtil.offsetDay(new Date(), i).toJdkDate();
            keys.add(prifix+DateUtil.format(date,DatePattern.PURE_DATE_FORMAT));
        }
        redisUtil.zUnionAndStore(key,keys,"last_week_rank");
    }
}
