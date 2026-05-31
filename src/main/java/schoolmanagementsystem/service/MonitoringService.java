package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.SensorDashboardDto;
import schoolmanagementsystem.mapper.SensorReadingMapper;
import schoolmanagementsystem.repository.SensorReadingRepository;

import java.util.List;

@Service
public class MonitoringService {

    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    public MonitoringService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public List<SensorDashboardDto> getDashboard() {

        return sensorReadingRepository
                .findLatestReadings()
                .stream()
                .map(SensorReadingMapper::toSensorDashboardDto)
                .toList();
    }
}
