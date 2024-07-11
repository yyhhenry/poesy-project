package cn.d619.poesy.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // This annotation marks this exception with a 409
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
