package BusinessLayer;

import DataAccessLayer.DTOs.DCourse;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CourseController {
    private final ConcurrentHashMap<Integer, Course> courses;
    private int courseId = 0;

    public CourseController() {
        this.courses = new ConcurrentHashMap<>();
    }

    private void permissionValidator2(User user) {
    }
    // check if user has permission for lvl3
    private void permissionValidator3(User user) {
    }
    private void permissionValidator4(User user) {
    }
    private void permissionValidator5(User user) {
    }

    public void addCourse(String name, String generalInfo) {
        //check if name is valid
        courses.put(courseId, new Course(courseId, name, generalInfo, new DCourse(courseId++, name, generalInfo)));
    }
    public Course getCourse(int courseId) {
        if(courses.containsKey(courseId))
            return courses.get(courseId);
        throw new IllegalArgumentException("invalid course");
    }
    public void removeCourse(int courseId) {

    }
    public void addChat(String chatName, int courseId) {
        Course course = courses.get(courseId);
        if(course != null)
            course.addChat(chatName);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
    public void removeChat(int courseId) {
        Course course = courses.get(courseId);
        if(course != null)
            course.removeChat(courseId);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
    public void addChatMessage() {

    }
    public void removeChatMessage() {

    }
    public void markMessage() {

    }
    public void unMarkMessage() {

    }
    public void addStudent() {

    }
    public void removeStudent() {

    }
    public void addTeacher() {

    }
    public void removeTeacher() {

    }
    public void addExam() {

    }
    public void removeExam() {

    }
    public void addHomework() {

    }
    public void removeHomework() {

    }
    public void addAnswer(User user, int courseId, int examId, int questionId, String content, boolean correct) {
        permissionValidator3(user);
        Course course = courses.get(courseId);
        if(course != null)
            course.addAnswer(examId, questionId, content, correct);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }

    public void removeAnswer() {

    }
    public void addQuestion(User user, int examId, int courseId, double points, String title, List<PreExamAnswer> answers) {
        permissionValidator3(user);
        Course course = courses.get(courseId);
        if(course != null)
            course.addQuestion(examId, points, title, answers);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
    public void removeQuestion() {

    }
    public void changeCourseName(int courseId, String courseName) {

    }
    public void changeCourseGeneralInfo(int courseId, String generalInfo) {

    }

    public void loadData() {
    }
}
