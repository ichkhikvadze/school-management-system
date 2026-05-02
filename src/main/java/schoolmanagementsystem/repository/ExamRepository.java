package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schoolmanagementsystem.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
