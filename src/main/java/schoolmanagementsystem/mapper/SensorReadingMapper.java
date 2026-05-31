package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.SensorDashboardDto;
import schoolmanagementsystem.entity.SensorReading;

public class SensorReadingMapper {

    public static SensorDashboardDto toSensorDashboardDto(SensorReading sensorReading) {
        SensorDashboardDto  sensorDashboardDto = new SensorDashboardDto();

        Double value = sensorReading.getReadingValue();
        Double min = sensorReading.getSensor().getMinThreshold();
        Double max = sensorReading.getSensor().getMaxThreshold();
        boolean warning = value < min || value > max;
        String warningMessage = "";

        if (value < min) {
            warningMessage = "Below minimum threshold";
        }

        if (value > max) {
            warningMessage = "Above maximum threshold";
        }

        sensorDashboardDto.setSensorName(sensorReading.getSensor().getName());
        sensorDashboardDto.setSensorType(sensorReading.getSensor().getType().name());
        sensorDashboardDto.setLocation(sensorReading.getSensor().getLocation());
        sensorDashboardDto.setValue(value);
        sensorDashboardDto.setUnit(sensorReading.getSensor().getUnit());
        sensorDashboardDto.setMinThreshold(min);
        sensorDashboardDto.setMaxThreshold(max);
        sensorDashboardDto.setWarning(warning);
        sensorDashboardDto.setWarningMessage(warningMessage);

        return sensorDashboardDto;
    }
}
