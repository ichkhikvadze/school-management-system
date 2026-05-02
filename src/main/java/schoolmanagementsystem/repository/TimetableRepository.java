package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.TimeTable;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTable, Long> {
}
