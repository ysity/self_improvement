package com.crescentd.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
@Data
public class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date modified;
}
