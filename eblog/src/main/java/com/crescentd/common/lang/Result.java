package com.crescentd.common.lang;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
@Data
public class Result {

    private String code = "200";
    private String msg;
    private Object data;
    private String action;

    public static Result succ() {
        return Result.succ("操作成功", null);
    }

    public static Result succ(Object data){
        Result result = new Result();
        result.setCode("200");
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static Result succ(String message,Object data){
        Result result = new Result();
        result.setData(data);
        result.setMsg(message);
        result.setCode("200");
        return result;
    }


    public static Result fail(String message){
        Result result = new Result();
        result.setMsg(message);
        result.setCode("500");
        result.setData(null);
        return result;
    }

    public static Result fail(String message,@Nullable String code){
        Result result = new Result();
        result.setCode(code==null?"500":code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }

    public static Result fail(String message,Object data){
        Result result = new Result();
        result.setCode("500");
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public Result action(String action){
        this.action = action;
        return this;
    }
}
