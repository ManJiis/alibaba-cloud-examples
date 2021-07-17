package top.b0x0.cloud.alibaba.common.exception;

/**
 * 业务异常提示信息枚举类
 *
 * @author ManJiis
 */
public enum BusinessMsgEnum {
    /**
     * 参数异常
     */
    PARMETER_EXCEPTION(102, "参数异常!"),

    /**
     * 等待超时
     */
    SERVICE_TIME_OUT(103, "服务调用超时！"),

    /**
     * 500 : 发生异常
     */
    UNEXPECTED_EXCEPTION(500, "服务器开小差了,请稍后重试!");

    /**
     * 消息码
     */
    private final Integer code;
    /**
     * 消息内容
     */
    private final String message;

    private BusinessMsgEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
