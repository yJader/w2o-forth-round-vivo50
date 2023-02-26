package com.yj.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    NOT_FOUND(404, "无法找到资源"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    FILE_TYPE_ERROR(506, "文件类型错误, 请上传png/jpg文件"),
    NICKNAME_EXIST(507, "昵称已存在"),
    THE_NUMBER_OF_QUERIES_IS_TOO_LARGE(508, "查询数量过大"),
    REPEATED_SIGN_IN(509, "重复签到"),
    SIGN_IN_TIME_ERROR(510, "签到时间错误 (我超 时空穿越者)"),
    INSUFFICIENT_POINTS(511, "积分不足"),
    PROJECT_NOT_EXIST(512, "访问项目不存在"),
    NO_QUERY_CRITERIA_ENTERED(513, "未输入查询条件"),
    QUERY_RESULT_IS_EMPTY(514, "查询结果为空"),
    PROJECT_CANNOT_BE_MODIFIED(515, "已发布项目不能修改"),
    ;
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
