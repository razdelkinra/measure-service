package com.offside.game.gascounter.service;

import com.offside.game.gascounter.GasCounterApplication;
import com.offside.game.gascounter.entity.GasMeasure;
import com.offside.game.gascounter.entity.MeasureType;
import com.offside.game.gascounter.entity.WaterMeasure;
import com.offside.game.gascounter.entity.WaterType;
import com.offside.game.gascounter.model.dto.MeasureDto;
import com.offside.game.gascounter.model.dto.WaterMeasureDto;
import com.offside.game.gascounter.model.response.MeasureHistoryResponse;
import com.offside.game.gascounter.repository.GasMeasureRepository;
import com.offside.game.gascounter.repository.WaterMeasureRepository;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GasCounterApplication.class)
class MeasureServiceTest {

    @Autowired
    MeasureService measureService;
    @Autowired
    GasMeasureRepository gasMeasureRepository;
    @Autowired
    WaterMeasureRepository waterMeasureRepository;

    @Test
    public void shouldSaveNewRequest() {
        String userId = "1";
        BigDecimal value = new BigDecimal("10.1");
        WaterType cold = WaterType.COLD;
        WaterMeasureDto request = getWaterMeasureDto(value, userId, cold);
        measureService.publish(request);

        val data = waterMeasureRepository.findAll();
        WaterMeasure result = data.iterator().next();
        Assertions.assertEquals(value, result.getValue().stripTrailingZeros());
        Assertions.assertEquals(cold, result.getTemperType());
        Assertions.assertEquals(MeasureType.WATER, result.getType());
        Assertions.assertEquals(userId, result.getUserId());
    }

    @Test
    public void shouldReturnHistory() {
        BigDecimal value = new BigDecimal("10.1");
        String userId = "2";
        WaterType cold = WaterType.COLD;

        WaterMeasure waterMeasure = new WaterMeasure();
        waterMeasure.setUserId(userId);
        waterMeasure.setTemperType(cold);
        waterMeasure.setValue(value);

        GasMeasure gasMeasure = new GasMeasure();
        gasMeasure.setUserId(userId);
        gasMeasure.setValue(value);

        waterMeasureRepository.save(waterMeasure);
        gasMeasureRepository.save(gasMeasure);

        MeasureHistoryResponse history = measureService.history(userId);
        List<MeasureDto> historyMeasures = history.getHistoryMeasures();

        Assertions.assertEquals(2, historyMeasures.size());
        WaterMeasureDto result = (WaterMeasureDto) historyMeasures.get(1);
        Assertions.assertEquals(value, result.getValue().stripTrailingZeros());
        Assertions.assertEquals(cold, result.getWaterType());
        Assertions.assertEquals(MeasureType.WATER, result.getMeasureType());
        Assertions.assertEquals(userId, result.getUserId());

    }

    private WaterMeasureDto getWaterMeasureDto(BigDecimal value, String userId, WaterType cold) {
        WaterMeasureDto request = new WaterMeasureDto();
        request.setMeasureType(MeasureType.WATER);
        request.setValue(value);
        request.setUserId(userId);
        request.setWaterType(cold);
        return request;
    }


}