package BusinessLayer;

public abstract class ExamQuestion {
    private int points;
    private String title;

    public ExamQuestion(int points, String title) {
        this.points = points;
        this.title = title;
    }

    public int getPoints() {
        return points;
    }
}
