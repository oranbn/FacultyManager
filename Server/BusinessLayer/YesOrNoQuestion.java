package BusinessLayer;

public class YesOrNoQuestion extends ExamQuestion {
    private int correctAnswer; // 0 false, 1 true
    public YesOrNoQuestion(int points, String title, int correctAnswer) {
        super(points, title);
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
