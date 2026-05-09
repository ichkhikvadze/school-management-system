package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentViewDto;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.mapper.StudentMapper;
import schoolmanagementsystem.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<StudentViewDto> getStudents(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("user.firstName").ascending());
        Page<Student> studentPage;

        if (search == null || search.isBlank()) {
            studentPage = studentRepository.findAll(pageable);
        } else {
            studentPage = studentRepository.searchStudents(search, pageable);
        }

        return studentPage.map(StudentMapper::toStudentViewDto);
    }
}
