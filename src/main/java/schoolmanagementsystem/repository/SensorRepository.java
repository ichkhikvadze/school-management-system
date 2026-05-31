package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
