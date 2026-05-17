package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Assignment;

import java.util.List;

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
}
