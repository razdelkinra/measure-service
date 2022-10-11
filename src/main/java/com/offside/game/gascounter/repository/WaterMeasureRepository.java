package com.offside.game.gascounter.repository;

import com.offside.game.gascounter.entity.WaterMeasure;
import org.springframework.stereotype.Repository;

@Repository("water")
public interface WaterMeasureRepository extends MeasureRepository<WaterMeasure> {
}
