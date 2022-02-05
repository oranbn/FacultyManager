package DataAccessLayer.DTOs;

import DataAccessLayer.DCourseChatController;
import DataAccessLayer.DExamController;
import DataAccessLayer.DalController;

public class DExam extends DTO{
    public static final String CourseIDColumnName="CourseID";
    public static final String DurationColumnName="Duration";
    public static final String IsOpenColumnName="IsOpen";
    public static final String ExamDateColumnName="ExamDate";
    private boolean persisted;
    private final int courseId;
    private int duration;
    private boolean isOpen;
    private String examDate;

    public DExam(int id, int courseId, int duration, boolean isOpen, String examDate) {
        super(new DExamController(), id);
        this.courseId = courseId;
        this.duration = duration;
        this.isOpen = isOpen;
        this.examDate = examDate;
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
    public void insert()
    {
        if(!persisted)
        {
            ((DExamController)controller).insert(this);
            persisted = true;
        }
    }
    public void setDuration(int duration) {
        this.duration = duration;
        if(persisted)
        {
            controller.update(getId(), DurationColumnName, duration);
        }
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
        if(persisted)
        {
            controller.update(getId(), IsOpenColumnName, isOpen);
        }
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
        if(persisted)
        {
            controller.update(getId(), ExamDateColumnName, examDate);
        }
    }
}
