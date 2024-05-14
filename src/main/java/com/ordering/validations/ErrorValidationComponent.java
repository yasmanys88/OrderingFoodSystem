package com.ordering.validations;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
@Slf4j
public class ErrorValidationComponent {

    public ResponseEntity<Map<String,String>> validationErrors(BindingResult result) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errorMap.put(error.getField(),error.getDefaultMessage());
        }
        log.error(result.getErrorCount()+" errors were found in the validated fields");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
