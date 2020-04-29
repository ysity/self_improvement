package com.crescentd.entity.vo;

import com.crescentd.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dyh
 * @description:
 * @date 2020/4/29
 **/
@Data
public class RequestVo implements Serializable{
    private String repassword;
    private User user;
    private String captcha;
}
