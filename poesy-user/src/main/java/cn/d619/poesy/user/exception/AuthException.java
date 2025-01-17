package cn.d619.poesy.user.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {
    private HttpStatus status;

    public AuthException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public AuthException(String message) {
        super(message);
        status = HttpStatus.CONFLICT;
    }
}
