package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.StudentTimetableDto;
import schoolmanagementsystem.dto.TeacherTimetableDto;
import schoolmanagementsystem.entity.Teacher;
import schoolmanagementsystem.entity.TimeTable;
import schoolmanagementsystem.entity.User;

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

    public static StudentTimetableDto toStudentTimetableDto(TimeTable timeTable) {
        StudentTimetableDto studentTimetableDto = new StudentTimetableDto();
        studentTimetableDto.setSubjectName(timeTable.getSubject().getName());
        studentTimetableDto.setDayOfWeek(timeTable.getDayOfWeek());
        User teacherUser = timeTable.getTeacher().getUser();
        String teacherName = teacherUser.getFirstName() + " " + teacherUser.getLastName();
        studentTimetableDto.setTeacherName(teacherName);
        studentTimetableDto.setRoomNumber(timeTable.getRoomNumber());
        studentTimetableDto.setStartTime(timeTable.getStartTime());
        studentTimetableDto.setEndTime(timeTable.getEndTime());
        return studentTimetableDto;
    }
}
