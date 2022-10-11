package com.offside.game.gascounter.service;

import com.offside.game.gascounter.entity.Measure;
import com.offside.game.gascounter.mapper.MeasureMapper;
import com.offside.game.gascounter.model.dto.MeasureDto;
import com.offside.game.gascounter.model.response.MeasureHistoryResponse;
import com.offside.game.gascounter.model.response.MeasureResponse;
import com.offside.game.gascounter.repository.MeasureRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class MeasureService {

    Map<String, MeasureRepository> repositoryMap;
    MeasureMapper measureMapper;
    ValidateService validateService;

    public MeasureResponse publish(MeasureDto request) {
        ValidateResponse validateResponse = validateService.validate(request);
        if (validateResponse.isHasError()) {
            return new MeasureResponse(validateResponse.getMessage(), true);
        } else {
            try {
                val measureRepository = repositoryMap.get(request.getMeasureType().name().toLowerCase());
                measureRepository.save(measureMapper.toMeasureEntity(request));
                return new MeasureResponse("Measure was saved", false);
            } catch (Exception e) {
                log.error("Error publish new measure with message {}", e.getMessage());
                return new MeasureResponse(e.getMessage(), true);
            }
        }
    }

    public MeasureHistoryResponse history(String userId) {
        try {
            List<Measure> measures = getMeasures(userId);
            return new MeasureHistoryResponse(measures.stream().map(measureMapper::toDto).collect(Collectors.toList()), null, false);
        } catch (Exception e) {
            log.error("Error get history with message {}", e.getMessage());
            return new MeasureHistoryResponse(new ArrayList<>(), "Error get history", true);
        }
    }

    public List<Measure> getMeasures(String userId) {
        List<Measure> collect = (List<Measure>) repositoryMap.keySet().stream()
                .map(type -> CompletableFuture.supplyAsync(() -> repositoryMap.get(type).findByUserId(userId)))
                .map(CompletableFuture::join)
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        return collect;
    }
}
