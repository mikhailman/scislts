package com.verishko.scislt.controller;

import com.verishko.scislt.dto.MeasurementDTO;
import com.verishko.scislt.exception.ErrorResponse;
import com.verishko.scislt.exception.MeasurementNotCreatedException;
import com.verishko.scislt.model.Measurement;
import com.verishko.scislt.service.MeasurementService;
import com.verishko.scislt.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
//@RequestMapping("sensor" + "${uuid}" +"/measurements")
public class MeasurementsController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    // возвращает все измерения в json
    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        LocalDateTime currentTime = LocalDateTime.now();
        return measurementService.findAll()
                .stream().filter(x -> x.getMeasurementDateTime().isAfter(currentTime.minusMinutes(1)))
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    // добавление измерения
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            // вывод ошибок в сторонний класс
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                        .append("; ");
            }
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }
        measurementService.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    // обработка ошибок контроллера измерений
    @ExceptionHandler(MeasurementNotCreatedException.class)
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
