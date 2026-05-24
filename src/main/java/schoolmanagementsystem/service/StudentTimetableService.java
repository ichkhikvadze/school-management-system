package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.StudentTimetableDto;
import schoolmanagementsystem.mapper.TimeTableMapper;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.List;

@Service
public class StudentTimetableService {

    private TimetableRepository timetableRepository;

    @Autowired
    public StudentTimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public List<StudentTimetableDto> getStudentTimetable(String username) {

        return timetableRepository
                .findStudentTimetable(username)
                .stream()
                .map(TimeTableMapper::toStudentTimetableDto)
                .toList();
    }
}
