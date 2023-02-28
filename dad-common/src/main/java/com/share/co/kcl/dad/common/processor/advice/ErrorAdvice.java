package com.share.co.kcl.dad.common.processor.advice;

import com.share.co.kcl.dad.common.constants.ResultCode;
import com.share.co.kcl.dad.common.exception.BusinessException;
import com.share.co.kcl.dad.common.exception.HttpException;
import com.share.co.kcl.dad.common.exception.ToastException;
import com.share.co.kcl.dad.common.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
public class ErrorAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return Result.error(ResultCode.PARAMS_ERROR);
    }

    @ExceptionHandler(HttpException.class)
    public Result<Void> httpExceptionHandler(HttpException ex) {
        return Result.error(ResultCode.NETWORK_ERROR);
    }

    @ExceptionHandler(ToastException.class)
    public Result<Void> toastExceptionHandler(ToastException ex) {
        return Result.error(ResultCode.OPERATE_ERROR, ex);
    }

    @ExceptionHandler(BusinessException.class)
    public Result<Void> businessExceptionHandler(BusinessException ex) {
        return Result.error(ResultCode.BUSINESS_ERROR, ex);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> exceptionHandler(Exception ex) {
        LOG.error("unknown error,", ex);
        return Result.error(ResultCode.ERROR);
    }

}
