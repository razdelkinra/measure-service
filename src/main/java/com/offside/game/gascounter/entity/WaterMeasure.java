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
@Entity(name = "water_measure")
public class WaterMeasure extends Measure {

    WaterType temperType;

    @JsonIgnore
    @Override
    public MeasureType getType() {
        return MeasureType.WATER;
    }
}
