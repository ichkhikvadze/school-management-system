package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.entity.Sensor;
import schoolmanagementsystem.entity.SensorReading;
import schoolmanagementsystem.repository.SensorReadingRepository;
import schoolmanagementsystem.repository.SensorRepository;
import schoolmanagementsystem.request.CreateSensorReadingRequest;
import schoolmanagementsystem.response.CreateReadingResponse;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SensorReadingService {

    private SensorRepository sensorRepository;
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    public SensorReadingService(SensorRepository sensorRepository, SensorReadingRepository sensorReadingRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorReadingRepository = sensorReadingRepository;
    }

    @Transactional
    public CreateReadingResponse createReading(Long sensorId, CreateSensorReadingRequest request) {

        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        Optional<SensorReading> sensorReadingOptional = sensorReadingRepository.findBySensorId(sensorId);
        if (sensorReadingOptional.isPresent()) {
            SensorReading sensorReading = sensorReadingOptional.get();
            sensorReading.setSensor(sensor);
            sensorReading.setReadingValue(request.getValue());
            sensorReading.setReadingTime(LocalDateTime.now());

            new CreateReadingResponse("Reading saved");
        }

        return new CreateReadingResponse("Reading failed");
    }
}
