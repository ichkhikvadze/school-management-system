package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Exam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @EntityGraph(attributePaths = {
            "group",
            "subject"
    })
    @Query("""
        SELECT e
        FROM Exam e
        WHERE e.group.id = :groupId
        ORDER BY e.examDate DESC
    """)
    List<Exam> findGroupExams(Long groupId);

    @EntityGraph(attributePaths = {
            "group",
            "group.students",
            "group.students.user",
            "subject"
    })
    Optional<Exam> findDetailedById(Long id);
}
