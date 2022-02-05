package BusinessLayer;

import java.util.concurrent.ConcurrentHashMap;

public class Exam {
    private final ConcurrentHashMap<Integer, ExamQuestion> examQuestions;
    private int courseId;
    private int duration;
    private boolean isOpen;
    private String examDate;
    private final ConcurrentHashMap<User, Integer> grades;

    public Exam(int courseId, int duration, String examDate) {
        this.examQuestions = new ConcurrentHashMap<>();
        this.courseId = courseId;
        this.duration = duration;
        this.examDate = examDate;
        this.isOpen = false;
        this.grades = new ConcurrentHashMap<>();
    }

    public void addQuestion()
    {

    }
    public void openExam()
    {
        isOpen = true;
    }
    public void closeExam()
    {
        isOpen = false;
    }
    public void calculateGrades()
    {

    }
}
