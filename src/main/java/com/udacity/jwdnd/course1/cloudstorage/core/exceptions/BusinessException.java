package com.udacity.jwdnd.course1.cloudstorage.core.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ServiceError serviceError;

    public BusinessException(ServiceError serviceError) {
        super("Business error occurred: %s (%s) [params: %s]".formatted(serviceError.getErrorMessageKey(),
                                                                        serviceError.getErrorId(),
                                                                        serviceError.getParams()));
        this.serviceError = serviceError;
    }
}
