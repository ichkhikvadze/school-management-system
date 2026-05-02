package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.entity.StudentGroup;
import schoolmanagementsystem.repository.StudentGroupRepository;

import java.util.List;

@Service
public class StudentGroupService {

    private StudentGroupRepository studentGroupRepository;

    @Autowired
    public StudentGroupService(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    public List<StudentGroup> getStudentGroupList() {
        return studentGroupRepository.findAll();
    }
}
