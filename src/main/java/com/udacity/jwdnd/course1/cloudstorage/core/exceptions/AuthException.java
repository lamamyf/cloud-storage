package com.udacity.jwdnd.course1.cloudstorage.core.exceptions;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthException extends AuthenticationException {

    private final ServiceError serviceError;

    public AuthException(ServiceError serviceError) {
        super("Authentication error occurred: %s (%s) [params: %s]".formatted(serviceError.getErrorMessageKey(),
                                                                              serviceError.getErrorId(),
                                                                              serviceError.getParams()));
        this.serviceError = serviceError;
    }
}
