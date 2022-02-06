package BusinessLayer;

import java.util.List;

public class ExamQuestion {
    private final int examId;
    private int points;
    private String title;
    private final List<ExamAnswer> answers;

    public ExamQuestion(int examId, int points, String title, List<ExamAnswer> answers) {
        this.examId = examId;
        this.points = points;
        this.title = title;
        this.answers = answers;
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
