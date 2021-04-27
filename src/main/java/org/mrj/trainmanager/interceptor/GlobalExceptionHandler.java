package org.mrj.trainmanager.interceptor;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.mrj.trainmanager.common.enums.ErrorCodeEnums;
import org.mrj.trainmanager.common.exception.TrainException;
import org.mrj.trainmanager.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @Autowired
    private Gson gson;

    @ExceptionHandler(value = TrainException.class)
    public Response bizExceptionHandler(TrainException e) {
        log.error("业务异常!", e);
        return Response.fail(e);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Response nullExceptionHandler(NullPointerException e) {
        log.error("空指针异常!", e);
        return Response.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMsg = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errorMsg.add(error.getDefaultMessage());
        }
        log.error("GlobalExceptionHandler#exception {}", exception);
        return Response.fail(new TrainException(ErrorCodeEnums.PARAM), gson.toJson(errorMsg));
    }

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(HttpServletRequest request,
                                     HttpServletResponse response, Object handler, Exception ex) {
        log.error("服务端异常,request={},response={},handler={},ex={}", request, response, handler, ex);
        return Response.fail(new TrainException(ErrorCodeEnums.SERVER_INTERVAL_EXCEPTION));
    }

}