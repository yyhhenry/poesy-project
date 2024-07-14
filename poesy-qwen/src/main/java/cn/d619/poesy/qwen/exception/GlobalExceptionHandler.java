package cn.d619.poesy.qwen.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerResponse;
import cn.d619.poesy.qwen.pojo.dto.ErrorDTO;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public Mono<ServerResponse> handleAuthException(HttpException e) {
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage());
        return ServerResponse.status(e.getStatus()).bodyValue(errorDTO);
    }

}
