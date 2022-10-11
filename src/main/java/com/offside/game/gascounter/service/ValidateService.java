package com.offside.game.gascounter.service;

import com.offside.game.gascounter.model.dto.HistoryRequest;
import com.offside.game.gascounter.model.dto.MeasureDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ValidateService {

    Validator validator;

    public ValidateResponse validate(MeasureDto measureDto) {
        return getValidateResponse(measureDto);
    }

    private <T> ValidateResponse getValidateResponse(T request) {
        val violations = validator.validate(request);
        if (violations.size() > 0) {
            String errorMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            return new ValidateResponse(errorMessage, true);
        } else {
            return new ValidateResponse("Ok", false);
        }
    }

}
