package schoolmanagementsystem.mapper;

import schoolmanagementsystem.dto.ExamViewDto;
import schoolmanagementsystem.entity.Exam;

public class ExamMapper {

    public static ExamViewDto toExamViewDto(Exam exam) {
        ExamViewDto examViewDto = new ExamViewDto();
        examViewDto.setId(exam.getId());
        examViewDto.setTitle(exam.getTitle());
        examViewDto.setSubjectName(exam.getSubject().getName());
        examViewDto.setExamDate(exam.getExamDate());
        return examViewDto;
    }
}
