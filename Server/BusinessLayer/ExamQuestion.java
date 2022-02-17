package BusinessLayer;

import DataAccessLayer.DTOs.DExamAnswer;
import DataAccessLayer.DTOs.DExamQuestion;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ExamQuestion {
    private final int questionId;
    private final int examId;
    private final int coureId;
    private double points;
    private String title;
    private int answerCounter;
    private final ConcurrentHashMap<Integer, ExamAnswer> answers;
    private final DExamQuestion dExamQuestion;

    public ExamQuestion(int questionId, int examId, int coureId, double points, String title, List<PreExamAnswer> answers, DExamQuestion dExamQuestion) {
        this.questionId = questionId;
        this.examId = examId;
        this.coureId = coureId;
        this.points = points;
        this.title = title;
        this.answers = new ConcurrentHashMap<>();
        for(PreExamAnswer preExamAnswer : answers)
        {
            this.answers.put(answerCounter, new ExamAnswer(questionId, answerCounter, examId, coureId, preExamAnswer.getContent(), preExamAnswer.isCorrect(), new DExamAnswer(answerCounter++, questionId, examId, coureId, preExamAnswer.getContent(), preExamAnswer.isCorrect())));
        }
        this.dExamQuestion = dExamQuestion;
        dExamQuestion.insert();
    }

    public ExamQuestion(DExamQuestion dExamQuestion)
    {
        this.questionId = dExamQuestion.getId();
        this.examId = dExamQuestion.getExamId();
        this.coureId = dExamQuestion.getCourseId();
        this.points = dExamQuestion.getPoints();
        this.title = dExamQuestion.getTitle();
        answers = new ConcurrentHashMap<>();
        this.dExamQuestion = dExamQuestion;
        //todo:
        // load all answers here.

    }
    public int getQuestionId() {
        return questionId;
    }
    public double getPoints() {
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
    public ConcurrentHashMap<Integer, ExamAnswer> getAnswers() {
        return answers;
    }
    public void addAnswer(String content, boolean correct)
    {
        this.answers.put(answerCounter, new ExamAnswer(questionId, answerCounter, examId, coureId, content, correct, new DExamAnswer(answerCounter++, questionId, examId, coureId, content, correct)));

    }
    public void removeAnswer(ExamAnswer answer)
    {
        answers.remove(answer);
    }

}
