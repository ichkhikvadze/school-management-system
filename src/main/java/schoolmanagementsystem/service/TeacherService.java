package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.TeacherViewDto;
import schoolmanagementsystem.entity.Teacher;
import schoolmanagementsystem.mapper.TeacherMapper;
import schoolmanagementsystem.repository.TeacherRepository;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Page<TeacherViewDto> getTeachers(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("user.firstName").ascending());

        Page<Teacher> teacherPage;

        if (search == null || search.isBlank()) {
            teacherPage = teacherRepository.findAll(pageable);
        } else {
            teacherPage = teacherRepository.searchTeachers(search, pageable);
        }

        return teacherPage.map(TeacherMapper::toTeacherViewDto);
    }
}
