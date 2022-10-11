package com.offside.game.gascounter.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.offside.game.gascounter.model.dto.MeasureDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasureHistoryResponse {
    List<MeasureDto> historyMeasures;
    String message;
    @JsonIgnore
    boolean invalid;
}
