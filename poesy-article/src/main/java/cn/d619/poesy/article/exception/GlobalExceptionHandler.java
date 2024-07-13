package cn.d619.poesy.article.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cn.d619.poesy.article.pojo.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorDTO> handleAuthException(HttpException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getMessage()));
    }

}
