package BusinessLayer;

import DataAccessLayer.DTOs.DExamAnswer;

public class ExamAnswer {
    private final int questionId;
    private int answerId;
    private final int examId;
    private final int courseId;
    private String content;
    private boolean correct;
    private final DExamAnswer dExamAnswer;
    public ExamAnswer(int questionId, int answerId, int examId, int courseId, String content, boolean correct, DExamAnswer dExamAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.examId = examId;
        this.courseId = courseId;
        this.content = content;
        this.correct = correct;
        this.dExamAnswer = dExamAnswer;
        dExamAnswer.insert();
    }

    public ExamAnswer(DExamAnswer dExamAnswer)
    {
        this.questionId = dExamAnswer.getQuestionId();
        this.answerId = dExamAnswer.getId();
        this.examId = dExamAnswer.getExamId();
        this.courseId = dExamAnswer.getCourseId();
        this.content = dExamAnswer.getContent();
        this.correct = dExamAnswer.isCorrect();
        this.dExamAnswer = dExamAnswer;
    }
    public int getExamId() { return examId; }

    public int getCourseId() { return courseId; }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerId() {
        return answerId;
    }
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    public void delete()
    {
        dExamAnswer.delete();
    }
}
