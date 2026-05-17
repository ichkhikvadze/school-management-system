package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Assignment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @EntityGraph(attributePaths = {
            "group",
            "subject",
            "teacher"
    })
    @Query("""
        SELECT a
        FROM Assignment a
        WHERE a.group.id = :groupId
        ORDER BY a.id DESC
    """)
    List<Assignment> findGroupAssignments(
            Long groupId
    );

    @EntityGraph(attributePaths = {
            "group",
            "group.students",
            "group.students.user",
            "subject",
            "teacher"
    })
    Optional<Assignment> findDetailedById(Long id);

    @Query("""
        SELECT COUNT(a) > 0
        FROM Assignment a
        JOIN a.teacher t
        JOIN t.user u
        WHERE
            a.id = :assignmentId
            AND
            u.username = :username
    """)
    boolean teacherOwnsAssignment(Long assignmentId, String username);
}
