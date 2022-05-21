package com.udacity.jwdnd.course1.cloudstorage.core.exceptions;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors.FileBusinessError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ResourceBundleMessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handelBusinessException(BusinessException exception) {
        return new ModelAndView("result", "error", exception.getServiceError().withTranslatingErrorMessage(messageSource).getErrorMessage());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ModelAndView handelSizeLimitExceededException(MaxUploadSizeExceededException exception){
        return new ModelAndView("result", "error", FileBusinessError.MAXIMUM_UPLOAD_SIZE_EXCEEDED
                                                                                         .toServiceError(exception.getMostSpecificCause().getMessage())
                                                                                         .withTranslatingErrorMessage(messageSource).getErrorMessage());
    }
}
