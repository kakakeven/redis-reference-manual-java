package com.lintrip.springboot.redis.dto;

/**
 * 响应体
 */
public class BaseResponseObject<T> {

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应 code
     */
    private Integer code;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResponseObject<T> success(T data) {
        BaseResponseObject<T> dataObject = new BaseResponseObject<>();
        dataObject.setData(data);
        dataObject.setCode(200);
        dataObject.setMessage("success");

        return dataObject;
    }
}
