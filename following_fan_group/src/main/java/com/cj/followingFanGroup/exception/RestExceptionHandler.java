package com.cj.followingFanGroup.exception;

import com.cj.followingFanGroup.common.response.BaseResponse;
import com.cj.followingFanGroup.common.response.ResponseCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(Exception.class)
    public BaseResponse<String> exception(Exception e) {
        return BaseResponse.fail(ResponseCode.RC500.getCode(), e.getMessage());
    }


    // 针对于请求参数校验不通过抛出的异常捕获
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> paramsException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        List<String> errorList = new ArrayList<>(); // 存放所有校验不通过的参数

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError error : fieldErrors) {
                errorList.add(error.getDefaultMessage());
            }
        }

        return BaseResponse.fail(ResponseCode.RC500.getCode(), String.join(", ", errorList));
    }
}
