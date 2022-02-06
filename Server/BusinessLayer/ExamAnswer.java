package BusinessLayer;

public class ExamAnswer {
    private final int questionId;
    private int answerId;
    private String content;
    private boolean correct;

    public ExamAnswer(int questionId, int answerId, String content, boolean correct) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.content = content;
        this.correct = correct;
    }

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
}
