package com.offside.game.gascounter.repository;

import com.offside.game.gascounter.entity.GasMeasure;
import org.springframework.stereotype.Repository;

@Repository("gas")
public interface GasMeasureRepository extends MeasureRepository<GasMeasure> {
}
