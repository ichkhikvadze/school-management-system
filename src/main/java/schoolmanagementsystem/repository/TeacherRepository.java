package schoolmanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUserUsername(String username);

    @EntityGraph(attributePaths = {"user"})
    Page<Teacher> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    @Query("""
        SELECT t
        FROM Teacher t
        JOIN t.user u
        WHERE
            LOWER(u.firstName) LIKE LOWER(CONCAT('%', :pattern, '%'))
            OR
            LOWER(u.lastName) LIKE LOWER(CONCAT('%', :pattern, '%'))
    """)
    Page<Teacher> searchTeachers(String pattern, Pageable pageable);
}
