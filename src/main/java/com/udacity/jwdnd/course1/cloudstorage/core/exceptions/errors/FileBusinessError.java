package com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.ServiceError;

public enum FileBusinessError {
    FILE_NAME_ALREADY_USED("F-1"),
    MAXIMUM_UPLOAD_SIZE_EXCEEDED("F-2");

    private final String errorId;

    FileBusinessError(String errorId) {
        this.errorId = errorId;
    }

    public ServiceError toServiceError(Object... params){
        return new ServiceError(errorId, getClass().getSimpleName() + "." + name(), params);
    }

    public String getErrorId() {
        return errorId;
    }
}
