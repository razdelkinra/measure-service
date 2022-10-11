package com.offside.game.gascounter.controller;

import com.offside.game.gascounter.model.dto.MeasureDto;
import com.offside.game.gascounter.model.response.MeasureHistoryResponse;
import com.offside.game.gascounter.model.response.MeasureResponse;
import com.offside.game.gascounter.service.MeasureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@ApiOperation("Measure API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MeasureMonitoringController {

    MeasureService measureService;


    // TODO: Should getting userId from token, secure problem
    @ApiOperation(value = "Publish new measure", notes = "Returns ok result or error message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    @PostMapping(path = "publish")
    public ResponseEntity<MeasureResponse> publish(@RequestBody MeasureDto measureDto) {
        val response = measureService.publish(measureDto);
        if (response.isInvalid()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(response);
        }
    }

    // TODO: Should getting userId from token, secure problem
    @ApiOperation(value = "Get history measures by user", notes = "Returns list of measures for user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    @GetMapping(path = "history")
    public ResponseEntity<MeasureHistoryResponse> history(@RequestParam String userId) {
        MeasureHistoryResponse response = measureService.history(userId);
        if (response.isInvalid()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return response.getHistoryMeasures().size() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
        }
    }

}
