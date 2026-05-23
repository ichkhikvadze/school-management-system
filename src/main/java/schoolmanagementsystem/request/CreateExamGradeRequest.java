package schoolmanagementsystem.request;

import java.math.BigDecimal;

public class CreateExamGradeRequest {

    private Long studentId;
    private BigDecimal score;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
