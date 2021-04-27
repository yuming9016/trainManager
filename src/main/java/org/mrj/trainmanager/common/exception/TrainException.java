package org.mrj.trainmanager.common.exception;

import org.mrj.trainmanager.common.enums.ErrorCodeEnums;

/**
 * @作者 s.1228
 * @日期 2021/2/25 22:19
 * @Version 1.0
 */

public class TrainException extends RuntimeException{
    protected int code;
    protected String msg;

    //出现服务器内部错误时
    public TrainException(){
        this.code = ErrorCodeEnums.SERVER_INTERVAL_EXCEPTION.getCode();
        this.msg = ErrorCodeEnums.SERVER_INTERVAL_EXCEPTION.getMsg();
    }

    //出现ErrorCodeEnums类里的异常时
    public TrainException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    //在上一层的catch中就可以捕获到NP异常
    public TrainException(int code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    //出现意料之外的错误时
    public TrainException(IexceptionCode exception) {
        this.code = exception.getCode();
        this.msg = exception.getMsg();
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getMessage() {
        return super.getMessage() == null ? this.toString() : msg;
    }

    @Override
    public String toString() {
        return "TrainException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
