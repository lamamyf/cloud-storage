package com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.ServiceError;

public enum AuthError {
    USERNAME_ALREADY_USED("U-1"),
    INVALID_CREDENTIALS("U-2");

    private final String errorId;

    AuthError(String errorId) {
        this.errorId = errorId;
    }
    public ServiceError toServiceError(Object... params){
        return new ServiceError(errorId, getClass().getSimpleName() + "." + name(), params);
    }

    public String getErrorId() {
        return errorId;
    }
}
