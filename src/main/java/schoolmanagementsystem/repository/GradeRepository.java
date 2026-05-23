package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Grade;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @EntityGraph(attributePaths = {
            "student",
            "student.user"
    })
    @Query("""
        SELECT g
        FROM Grade g
        WHERE g.student.id = :studentId
    """)
    List<Grade> findStudentGrades(Long studentId);

    boolean existsByAssignmentIdAndStudentId(Long assignmentId, Long studentId);

    boolean existsByExamIdAndStudentId(Long examId, Long studentId);
}
