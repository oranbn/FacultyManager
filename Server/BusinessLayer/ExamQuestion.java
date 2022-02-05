package BusinessLayer;

import DataAccessLayer.DTOs.DExamQuestion;

public abstract class ExamQuestion {
    private int examId;
    private int points;
    private String title;
    private final DExamQuestion dExamQuestion;

    public ExamQuestion(int examId, int points, String title, DExamQuestion dExamQuestion) {
        this.examId = examId;
        this.points = points;
        this.title = title;
        this.dExamQuestion = dExamQuestion;
        //dExamQuestion.insert();
    }

    public int getPoints() {
        return points;
    }
    public String getTitle() {
        return title;
    }
    public int getExamId() {
        return examId;
    }
}
