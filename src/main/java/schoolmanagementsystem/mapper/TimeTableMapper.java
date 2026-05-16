package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.TeacherTimetableDto;
import schoolmanagementsystem.entity.TimeTable;

public class TimeTableMapper {

    public static TeacherTimetableDto toTeacherTimetableDto(TimeTable timeTable) {
        TeacherTimetableDto teacherTimetableDto = new TeacherTimetableDto();
        teacherTimetableDto.setGroupName(timeTable.getGroup().getName());
        teacherTimetableDto.setSubjectName(timeTable.getSubject().getName());
        teacherTimetableDto.setDayOfWeek(timeTable.getDayOfWeek());
        teacherTimetableDto.setStartTime(timeTable.getStartTime());
        teacherTimetableDto.setEndTime(timeTable.getEndTime());
        teacherTimetableDto.setRoomNumber(timeTable.getRoomNumber());
        return teacherTimetableDto;
    }
}
