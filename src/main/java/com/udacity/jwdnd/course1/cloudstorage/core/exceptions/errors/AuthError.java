package com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.ServiceError;

public enum AuthError {
    INVALID_CREDENTIALS("U-1");

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
