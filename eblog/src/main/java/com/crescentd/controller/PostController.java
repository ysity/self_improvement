package com.crescentd.controller;


import com.crescentd.common.lang.Result;
import com.crescentd.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.crescentd.controller.BaseController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/hotpos")
    @ResponseBody
    public Result hotpos(){
        Set<ZSetOperations.TypedTuple> last_week_rank = redisUtil.getZSetRank("last_week_rank", 0, 6);
        List<Map<String, Object>> hotpos = new ArrayList<>();
        for (ZSetOperations.TypedTuple typedTuple : last_week_rank){
            Map<String, Object> map = new HashMap<>();
            map.put("comment_count",typedTuple.getScore());
            map.put("id",redisUtil.hget("rank_post_"+typedTuple.getValue(),"post:id"));
            map.put("title",redisUtil.hget("rank_post_"+typedTuple.getValue(),"post:title"));
            hotpos.add(map);
        }
        return Result.succ(hotpos);
    }

    @RequestMapping("/article")
    public String post(){
        return "post_article";
    }
}
