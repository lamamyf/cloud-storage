package com.udacity.jwdnd.course1.cloudstorage.core.exceptions;

import lombok.Getter;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
public class ServiceError {

    private String errorId;
    private String errorMessage;
    private String errorMessageKey;
    private List<String> params;

    public ServiceError(String errorId, String errorMessageKey, Object... params) {
        this.errorId = errorId;
        this.errorMessageKey = errorMessageKey;
        this.params = Arrays.stream(params).map(Object::toString).toList();
    }

    public ServiceError withTranslatingErrorMessage(ResourceBundleMessageSource messageSource){
        errorMessage = messageSource.getMessage(errorMessageKey, params.toArray(), Locale.ENGLISH);
        return this;
    }
}
