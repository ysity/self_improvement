package com.crescentd.shiro;

import java.util.Date;

/**
 * @author dyh
 * @description:
 * @date 2020/4/28
 **/
public class AccountProfile {

    private Long id;

    private String username;
    private String email;
    private String sign;

    private String avatar;
    private String gender;
    private Date created;

    public String getSex() {
        return "0".equals(gender) ? "女" : "男";
    }
}
