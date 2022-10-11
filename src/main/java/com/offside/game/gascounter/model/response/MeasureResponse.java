package com.offside.game.gascounter.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MeasureResponse {
    String message;
    @JsonIgnore
    boolean invalid;
}
