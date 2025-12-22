package com.example.demo.handler;

import com.example.demo.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author 柠檬树微服务平台
 */
@Slf4j
@RestController
@RestControllerAdvice
public class DefaultExceptionHandler {
  @ExceptionHandler({Exception.class})
  R<String> handleException(Exception e) {
    log.error("[系统异常 {}]", ExceptionUtils.getStackTrace(e));
    return R.error("系统异常," + e.getMessage());
  }
}
