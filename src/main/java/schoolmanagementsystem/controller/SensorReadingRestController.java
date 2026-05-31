package schoolmanagementsystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schoolmanagementsystem.request.CreateSensorReadingRequest;
import schoolmanagementsystem.response.CreateReadingResponse;
import schoolmanagementsystem.service.SensorReadingService;

@RestController
@RequestMapping("/api/sensors")
public class SensorReadingRestController {

    private SensorReadingService sensorReadingService;

    @Autowired
    public SensorReadingRestController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @PostMapping("/{sensorId}/readings")
    public ResponseEntity<CreateReadingResponse> createReading(
            @PathVariable Long sensorId,
            @Valid @RequestBody CreateSensorReadingRequest request) {
        CreateReadingResponse response = sensorReadingService.createReading(sensorId, request);
        return ResponseEntity.ok(response);
    }
}
