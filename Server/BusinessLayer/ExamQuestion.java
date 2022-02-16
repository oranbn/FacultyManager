package BusinessLayer;

import DataAccessLayer.DTOs.DExamQuestion;

import java.util.List;

public class ExamQuestion {
    private final int questionId;
    private final int examId;
    private final int coureId;
    private int points;
    private String title;
    private final List<ExamAnswer> answers;
    private final DExamQuestion dExamQuestion;

    public ExamQuestion(int questionId, int examId, int coureId, int points, String title, List<ExamAnswer> answers, DExamQuestion dExamQuestion) {
        this.questionId = questionId;
        this.examId = examId;
        this.coureId = coureId;
        this.points = points;
        this.title = title;
        this.answers = answers;
        this.dExamQuestion = dExamQuestion;
        // dExamQuestion.insert();
    }

    //todo:
    //constructor for DTO object
   /* public ExamQuestion(DExamQuestion dExamQuestion)
    {

    }*/
    public int getQuestionId() {
        return questionId;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<ExamAnswer> getAnswers() {
        return answers;
    }
    public void addAnswer(ExamAnswer answer)
    {
        answers.add(answer);
    }
    public void removeAnswer(ExamAnswer answer)
    {
        answers.remove(answer);
    }

}
