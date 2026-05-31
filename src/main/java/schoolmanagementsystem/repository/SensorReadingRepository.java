package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.SensorReading;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    @Query("""
        SELECT sr
        FROM SensorReading sr
        JOIN FETCH sr.sensor s
        WHERE sr.readingTime = (
            SELECT MAX(sr2.readingTime)
            FROM SensorReading sr2
            WHERE sr2.sensor.id = s.id)
    """)
    List<SensorReading> findLatestReadings();
}
