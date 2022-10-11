package com.offside.game.gascounter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "gas_measure")
public class GasMeasure extends Measure {

    @JsonIgnore
    @Override
    public MeasureType getType() {
        return MeasureType.GAS;
    }
}
