package cn.d619.poesy.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cn.d619.poesy.user.pojo.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDTO> handleAuthException(AuthException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorDTO(e.getMessage()));
    }

}
