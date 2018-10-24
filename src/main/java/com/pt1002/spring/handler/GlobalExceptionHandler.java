package com.pt1002.spring.handler;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler   {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> defaultExceptionHandler(Exception e){

        logger.error("统一错误处理===============>未知异常：",e);
        ResponseEntity response;
        if (StringUtils.isEmpty(e.getMessage()))
            response = new ResponseEntity<>(Response.UNKNOWN_EXCEPTION, HttpStatus.OK);
        else
            response = new ResponseEntity<>(
                    new Response(ResponseCode.UNKNOWN_EXCEPTION.getCode(),e.getMessage(),null),
                    HttpStatus.OK);
        return response;
    }
}
