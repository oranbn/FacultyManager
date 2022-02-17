package DataAccessLayer.DTOs;

import DataAccessLayer.*;

public class DExamQuestion extends DTO{
    public static final String CourseIDColumnName="CourseID";
    public static final String ExamIdColumnName="ExamId";
    public static final String PointsColumnName="Points";
    public static final String TitleColumnName="Title";

    private boolean persisted;
    private final int examId;
    private final int courseId;
    private double points;
    private String title;

    public DExamQuestion(int id, int examId, int courseId, double points, String title) {
        super(new DExamQuestionController(), id);
        this.courseId = courseId;
        this.examId = examId;
        this.points = points;
        this.title = title;
        persisted = false;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getExamId() {
        return examId;
    }

    public String getTitle() {
        return title;
    }

    public double getPoints() {
        return points;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DExamQuestionController)controller).insert(this);
            persisted = true;
        }
    }

    public void delete() {
        if(persisted)
        {
            ((DExamQuestionController)controller).delete(this);
        }
    }
}
