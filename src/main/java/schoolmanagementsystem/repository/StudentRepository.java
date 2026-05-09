package schoolmanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserUsername(String username);

    @EntityGraph(attributePaths = {"user", "groups"})
    Page<Student> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user", "groups"})
    @Query("""
        SELECT s
        FROM Student s
        JOIN s.user u
        WHERE
            LOWER(u.firstName) LIKE LOWER(CONCAT('%', :pattern, '%'))
            OR
            LOWER(u.lastName) LIKE LOWER(CONCAT('%', :pattern, '%'))
    """)
    Page<Student> searchStudents(String pattern, Pageable pageable);
}
