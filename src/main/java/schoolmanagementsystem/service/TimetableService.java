package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.TeacherTimetableDto;
import schoolmanagementsystem.mapper.TimeTableMapper;
import schoolmanagementsystem.repository.TimetableRepository;

import java.util.List;

@Service
public class TimetableService {

    private TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public List<TeacherTimetableDto> getTeacherTimetable(String username) {

        return timetableRepository
                .findTeacherTimetable(username)
                .stream()
                .map(TimeTableMapper::toTeacherTimetableDto)
                .toList();
    }
}
