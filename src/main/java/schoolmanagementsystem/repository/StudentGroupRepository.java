package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.StudentGroup;

import java.util.Optional;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    Optional<StudentGroup> findByName(String name);

    @EntityGraph(attributePaths = {
            "students",
            "students.user"
    })
    Optional<StudentGroup> findWithStudentsById(Long id);
}
