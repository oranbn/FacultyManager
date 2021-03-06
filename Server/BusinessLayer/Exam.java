package BusinessLayer;

import DataAccessLayer.DTOs.DExam;
import DataAccessLayer.DTOs.DExamQuestion;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Exam {
    private final ConcurrentHashMap<Integer, ExamQuestion> examQuestions;
    private int questionCounter;
    private final int courseId;
    private final int examId;
    private int duration;
    private boolean isOpen;
    private String examDate;
    private final ConcurrentHashMap<String, Integer> grades;
    private final DExam dExam;

    public Exam(int courseId,int examId, int duration, String examDate, DExam dExam) {
        this.examQuestions = new ConcurrentHashMap<>();
        this.courseId = courseId;
        this.examId = examId;
        this.duration = duration;
        this.examDate = examDate;
        this.isOpen = false;
        this.grades = new ConcurrentHashMap<>();
        this.dExam = dExam;
        questionCounter = 0;
        dExam.insert();
    }
    public Exam(DExam dExam)
    {
        this.examQuestions = new ConcurrentHashMap<>();
        this.courseId = dExam.getCourseId();
        this.examId = dExam.getId();
        this.duration = dExam.getDuration();
        this.examDate = dExam.getExamDate();
        this.isOpen = dExam.isOpen();
        this.grades = new ConcurrentHashMap<>();
        this.dExam = dExam;
        //todo:
        //load exam questions
        //load grades
        // set question counter
    }
    public void addQuestion(double points, String title, List<PreExamAnswer> answers)
    {
        examQuestions.put(questionCounter, new ExamQuestion(questionCounter, examId, courseId, points, title, answers, new DExamQuestion(questionCounter++, examId, courseId, points,title)));
    }
    public void changeDuration(int duration)
    {
        this.duration = duration;
        dExam.setDuration(duration);
    }
    public void openExam()
    {
        isOpen = true;
        dExam.setOpen(true);
    }
    public void closeExam()
    {
        isOpen = false;
        dExam.setOpen(false);
    }
    public void calculateGrades()
    {

    }
    public ConcurrentHashMap<Integer, ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getExamDate() {
        return examDate;
    }

    public int getGrade(String email) {
        return grades.get(email);
    }

    public void changeAnswerContent(int questionId, int answerId, String content) {
        ExamQuestion question = examQuestions.get(questionId);
        if(question!=null)
            question.changeAnswerContent(answerId, content);
        else
            throw new IllegalArgumentException("Question not found!");
    }

    public void changeAnswerCorrect(int questionId, int answerId, boolean correct) {
        ExamQuestion question = examQuestions.get(questionId);
        if(question!=null)
            question.changeAnswerCorrect(answerId, correct);
        else
            throw new IllegalArgumentException("Question not found!");
    }

    public void removeAnswer(int questionId, int answerId) {
        ExamQuestion question = examQuestions.get(questionId);
        if(question!=null)
            question.removeAnswer(answerId);
        else
            throw new IllegalArgumentException("Question not found!");
    }
}
