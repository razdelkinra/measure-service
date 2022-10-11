package com.offside.game.gascounter.model.dto;

import com.offside.game.gascounter.entity.WaterType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaterMeasureDto extends MeasureDto {
    @NotNull(message = "waterType must be not null")
    WaterType waterType;
}
