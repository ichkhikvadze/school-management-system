package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.StudentGroup;
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

    @Query("""
        SELECT COUNT(t) > 0
        FROM TimeTable t
        JOIN t.teacher teacher
        JOIN teacher.user u
        WHERE
            u.username = :username
            AND
            t.group.id = :groupId
    """)
    boolean teacherHasAccessToGroup(String username, Long groupId);

    @EntityGraph(attributePaths = {
            "group",
            "teacher"
    })
    @Query("""
        SELECT DISTINCT t.group
        FROM TimeTable t
        JOIN t.teacher teacher
        JOIN teacher.user u
        WHERE u.username = :username
        ORDER BY t.group.name
    """)
    List<StudentGroup> findGroupsByTeacherUsername(String username);

    @Query("""
        SELECT COUNT(t) > 0
        FROM TimeTable t
        JOIN t.group g
        JOIN g.students s
        JOIN t.teacher teacher
        JOIN teacher.user u
        WHERE
            u.username = :username
            AND
            s.id = :studentId
    """)
    boolean teacherHasAccessToStudent(String username, Long studentId);
}
