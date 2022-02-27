package DataAccessLayer.DTOs;

import DataAccessLayer.*;

public class DExamAnswer extends DTO{
    public static final String CourseIDColumnName="CourseID";
    public static final String QuestionIdColumnName="QuestionID";
    public static final String ExamIdColumnName="ExamID";
    public static final String ContentColumnName="Content";
    public static final String CorrectColumnName="Correct";

    private boolean persisted;
    private final int questionId;
    private final int examId;
    private final int courseId;
    private String content;
    private boolean correct;

    public DExamAnswer(int id, int questionId,int examId, int courseId, String content, boolean correct) {
        super(new DCourseChatController(), id);
        this.questionId = questionId;
        this.courseId = courseId;
        this.examId = examId;
        this.content = content;
        this.correct = correct;
        persisted = false;
    }

    public int getCourseId() {
        return courseId;
    }
    public int getQuestionId() {
        return questionId;
    }

    public int getExamId() {
        return examId;
    }

    public String getContent() {
        return content;
    }

    public boolean isCorrect() {
        return correct;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DExamAnswerController)controller).insert(this);
            persisted = true;
        }
    }

    public void delete() {
        if(persisted)
        {
            ((DExamAnswerController)controller).delete(this);
        }
    }
}
