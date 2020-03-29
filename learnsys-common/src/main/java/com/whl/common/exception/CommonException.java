package com.whl.common.exception;


import com.sun.org.apache.bcel.internal.classfile.Code;
import com.whl.common.enums.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception  {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
