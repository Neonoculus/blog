package org.example.blog.model;

import lombok.Data;

@Data
public class ResponseResult <T>{
    private Integer code;
    private String message;
    private T data;

    // 成功的响应，返回数据
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    // 400 - Bad Request
    public static <T> ResponseResult<T> badRequest(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(400);
        result.setMessage(message != null ? message : "请求无效");
        return result;
    }

    // 401 - Unauthorized
    public static <T> ResponseResult<T> unauthorized(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(401);
        result.setMessage(message != null ? message : "未授权");
        return result;
    }

    // 403 - Forbidden
    public static <T> ResponseResult<T> forbidden(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(403);
        result.setMessage(message != null ? message : "禁止访问");
        return result;
    }

    // 404 - Not Found
    public static <T> ResponseResult<T> notFound(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(404);
        result.setMessage(message != null ? message : "资源未找到");
        return result;
    }

    // 405 - Method Not Allowed
    public static <T> ResponseResult<T> methodNotAllowed(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(405);
        result.setMessage(message != null ? message : "不允许的请求方法");
        return result;
    }

    // 415 - Unsupported Media Type
    public static <T> ResponseResult<T> unsupportedMediaType(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(415);
        result.setMessage(message != null ? message : "不支持的媒体类型");
        return result;
    }

    // 500 - Internal Server Error
    public static <T> ResponseResult<T> internalServerError(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(500);
        result.setMessage(message != null ? message : "服务器内部错误");
        return result;
    }

    // 502 - Bad Gateway
    public static <T> ResponseResult<T> badGateway(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(502);
        result.setMessage(message != null ? message : "错误的网关");
        return result;
    }

    // 503 - Service Unavailable
    public static <T> ResponseResult<T> serviceUnavailable(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setCode(503);
        result.setMessage(message != null ? message : "服务不可用");
        return result;
    }
}
