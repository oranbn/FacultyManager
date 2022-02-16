package BusinessLayer;

public class PreExamAnswer {
    private String content;
    private boolean correct;

    public PreExamAnswer(String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }

    public String getContent() {
        return content;
    }

    public boolean isCorrect() {
        return correct;
    }
}
