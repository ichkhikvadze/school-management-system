package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schoolmanagementsystem.entity.*;
import schoolmanagementsystem.repository.RoleRepository;
import schoolmanagementsystem.repository.StudentRepository;
import schoolmanagementsystem.repository.TeacherRepository;
import schoolmanagementsystem.repository.UserRepository;
import schoolmanagementsystem.request.UserCreateRequest;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            StudentRepository studentRepository,
            TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public void createUser(UserCreateRequest request) {

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(role);

        userRepository.save(user);

        // AUTO CREATE ROLE-SPECIFIC TABLES
        if (request.getRole() == RoleName.STUDENT) {
            Student student = new Student();
            student.setUser(user);
            student.setEnrollmentNumber("STU-" + user.getId());
            studentRepository.save(student);
        }

        if (request.getRole() == RoleName.TEACHER) {
            Teacher teacher = new Teacher();
            teacher.setUser(user);
            teacherRepository.save(teacher);
        }
    }
}
