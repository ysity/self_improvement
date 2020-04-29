package com.crescentd.service.impl;

import com.crescentd.entity.UserMessage;
import com.crescentd.mapper.UserMessageMapper;
import com.crescentd.service.UserMessageService;
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
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

}
