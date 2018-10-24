package com.pt1002.common.exceptions;

/**
 * @Author: xubo
 * @Description:
 * @Date: Create in 9:21 2018/6/11
 * @Modified By:
 * @Test By:
 */
public class DataNotFoundException extends Exception{

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
