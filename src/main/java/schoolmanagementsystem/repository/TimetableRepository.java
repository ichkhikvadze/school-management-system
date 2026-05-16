package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.TimeTable;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<TimeTable, Long> {

    @EntityGraph(attributePaths = {
            "group",
            "subject",
            "teacher"
    })
    @Query("""
        SELECT t
        FROM TimeTable t
        JOIN t.teacher teacher
        JOIN teacher.user u
        WHERE u.username = :username
        ORDER BY
            t.dayOfWeek,
            t.startTime
    """)
    List<TimeTable> findTeacherTimetable(String username);
}
