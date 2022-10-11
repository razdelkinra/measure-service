package com.offside.game.gascounter.mapper;

import com.offside.game.gascounter.entity.GasMeasure;
import com.offside.game.gascounter.entity.Measure;
import com.offside.game.gascounter.entity.MeasureType;
import com.offside.game.gascounter.entity.WaterMeasure;
import com.offside.game.gascounter.model.dto.GasMeasureDto;
import com.offside.game.gascounter.model.dto.MeasureDto;
import com.offside.game.gascounter.model.dto.WaterMeasureDto;
import org.springframework.stereotype.Component;

@Component
public class MeasureMapper {

    public Measure toMeasureEntity(MeasureDto measureDto) {
        MeasureType type = measureDto.getMeasureType();
        Measure measure = null;
        switch (type) {
            case GAS:
                measure = toGasMeasure((GasMeasureDto) measureDto);
                break;
            case WATER:
                measure = getWaterMeasure((WaterMeasureDto) measureDto);
                break;
        }
        return measure;
    }

    private WaterMeasure getWaterMeasure(WaterMeasureDto waterMeasureDto) {
        WaterMeasure waterMeasure = new WaterMeasure();
        waterMeasure.setTemperType(waterMeasureDto.getWaterType());
        waterMeasure.setValue(waterMeasureDto.getValue());
        waterMeasure.setUserId(waterMeasureDto.getUserId());
        return waterMeasure;
    }

    private GasMeasure toGasMeasure(GasMeasureDto measureDto) {
        GasMeasure gasMeasure = new GasMeasure();
        gasMeasure.setValue(measureDto.getValue());
        gasMeasure.setUserId(measureDto.getUserId());
        return gasMeasure;
    }

    public MeasureDto toDto(Measure measure) {
        MeasureType type = measure.getType();
        MeasureDto measureDto = null;
        switch (type) {
            case GAS:
                measureDto = toGasMeasureDto((GasMeasure) measure);
                break;
            case WATER:
                measureDto = getWaterMeasureDto((WaterMeasure) measure);
                break;
        }
        return measureDto;
    }

    private WaterMeasureDto getWaterMeasureDto(WaterMeasure measure) {
        WaterMeasureDto waterMeasureDto = new WaterMeasureDto();
        waterMeasureDto.setWaterType(measure.getTemperType());
        waterMeasureDto.setValue(measure.getValue());
        waterMeasureDto.setUserId(measure.getUserId());
        waterMeasureDto.setMeasureType(MeasureType.WATER);
        return waterMeasureDto;
    }

    private GasMeasureDto toGasMeasureDto(GasMeasure measure) {
        GasMeasureDto measureDto = new GasMeasureDto();
        measureDto.setMeasureType(MeasureType.GAS);
        measureDto.setUserId(measure.getUserId());
        measureDto.setValue(measure.getValue());
        return measureDto;
    }
}
