package BusinessLayer;

import DataAccessLayer.DTOs.DCourse;

import java.util.concurrent.ConcurrentHashMap;

public class CourseController {
    private final ConcurrentHashMap<Integer, Course> courses;
    private int courseId = 0;

    public CourseController() {
        this.courses = new ConcurrentHashMap<>();
    }
    public void addCourse(String name, String generalInfo)
    {
        //check if name is valid
        courses.put(courseId, new Course(courseId, name, generalInfo, new DCourse(courseId++, name, generalInfo)));
    }
    public Course getCourse(int courseId)
    {
        if(courses.containsKey(courseId))
            return courses.get(courseId);
        throw new IllegalArgumentException("invalid course");
    }
    public void removeCourse(int courseId)
    {

    }
    public void addChat()
    {

    }
    public void removeChat()
    {

    }
    public void addChatMessage()
    {

    }
    public void removeChatMessage()
    {

    }
    public void markMessage()
    {

    }
    public void unMarkMessage()
    {

    }
    public void addStudent()
    {

    }
    public void removeStudent()
    {

    }
    public void addTeacher()
    {

    }
    public void removeTeacher()
    {

    }
    public void addExam()
    {

    }
    public void removeExam()
    {

    }
    public void addHomework()
    {

    }
    public void removeHomework()
    {

    }
    public void addAnswer()
    {

    }
    public void removeAnswer()
    {

    }
    public void addQuestion()
    {

    }
    public void removeQuestion()
    {

    }
    public void changeCourseName(int courseId, String courseName)
    {

    }
    public void changeCourseGeneralInfo(int courseId, String generalInfo)
    {

    }
}
