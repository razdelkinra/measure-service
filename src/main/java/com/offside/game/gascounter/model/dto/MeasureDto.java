package com.offside.game.gascounter.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.offside.game.gascounter.entity.MeasureType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "measureType",
        defaultImpl = MeasureType.class,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GasMeasureDto.class, name = "GAS"),
        @JsonSubTypes.Type(value = WaterMeasureDto.class, name = "WATER")}
)
public class MeasureDto {
    @NotNull(message = "userId must be not null")
    String userId;
    @NotNull(message = "value must be not null")
    BigDecimal value;
    @NotNull(message = "measureType must be not null")
    MeasureType measureType;

}
