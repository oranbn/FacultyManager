package BusinessLayer;

import DataAccessLayer.DCourseController;
import DataAccessLayer.DTOs.DCourse;
import DataAccessLayer.DTOs.DUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CourseController {
    private final ConcurrentHashMap<Integer, Course> courses;
    private final GeneralChat generalChat;
    private int courseId = 0;
    private final DCourseController dCourseController;

    public CourseController() {
        this.courses = new ConcurrentHashMap<>();
        generalChat = new GeneralChat();
        dCourseController = new DCourseController();
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

    public void addCourse(User user, String name, String generalInfo) {
        //check if user has permission to add the course
        if(ForbiddenWords.getInstance().getForbiddenWords().contains(name))
            throw new IllegalArgumentException("Course name is illegal");
        courses.put(courseId, new Course(courseId, name, generalInfo, new DCourse(courseId++, name, generalInfo)));
    }
    public List<Course> getCourses(String email, int permission) {
        if(permission==5)
            return new ArrayList<Course>(courses.values());
        List<Course> courses = new ArrayList<>();
        for(Course course : courses)
            if(course.getStudents().contains(email))
                courses.add(course);
        return courses;
    }
    public void removeCourse(int courseId) {

    }
    public void addChat(User user, String chatName, int courseId) {
        //todo:
        //check if user has permission to add new chat
        Course course = courses.get(courseId);
        if(course != null)
            course.addChat(chatName);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
    public void removeChat(User user, int courseId, int chatId) {
        Course course = courses.get(courseId);
        if(course != null)
            course.removeChat(chatId);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
    public void addChatMessage(User user, int courseId, int chatId, String content, String time) {
        if(courseId == -1)
            generalChat.addMessage(user.getEmail(), time, content);
        else{
            Course course = courses.get(courseId);
            if(course==null)
                throw new IllegalArgumentException("Course not found!");
            course.addMessage(user.getEmail(), chatId, content, time);
        }
    }
    public void removeChatMessage(User user, int courseId, int chatId, int messageId) {
        if(courseId == -1)
            generalChat.removeMessage(messageId);
        else{
            Course course = courses.get(courseId);
            if(course==null)
                throw new IllegalArgumentException("Course not found!");
            course.removeChatMessage(user.getEmail(), chatId, messageId);
        }
    }
    public void markMessage(int courseId, int chatId, int messageId) {
        if(courseId==-1)
            generalChat.markMessage(messageId);
        else {
            Course course = courses.get(courseId);
            if(course!=null)
                course.markMessage(chatId, messageId);
            else
                throw new IllegalArgumentException("Course not found!");
        }
    }
    public void unMarkMessage(int courseId, int chatId, int messageId) {
        if (courseId == -1)
            generalChat.unMarkMessage(messageId);
        else {
            Course course = courses.get(courseId);
            if (course != null)
                course.markMessage(chatId, messageId);
            else
                throw new IllegalArgumentException("Course not found!");
        }
    }
    public void addStudent() {

    }
    public void removeStudent() {

    }
    public void addTeacher() {

    }
    public void removeTeacher() {

    }
    public void addExam(User user, int courseId, int duration, String examDate) {
        //check if user has permission to add exam
        Course course = courses.get(courseId);
        if(course != null)
            course.addExam(user, duration, examDate);
        else
            throw new IllegalArgumentException("Invalid Course!");
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

    public void removeAnswer(User user, int courseId, int examId, int questionId, int answerId) {
        //check if user has permission to remove answer
        Course course = courses.get(courseId);
        if(course != null)
            course.removeAnswer(examId, questionId, answerId);
        else
            throw new IllegalArgumentException("Invalid Course!");
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
        List<DCourse> dcourses =  (List<DCourse>)(List<?>)dCourseController.select();
        for(DCourse dcourse : dcourses)
        {
            courses.put(dcourse.getId(), new Course(dcourse));
            courseId++;
        }
    }

    public void changeAnswerContent(User user, int courseId, int examId, int questionId, int answerId, String content) {
        permissionValidator3(user);
        Course course = courses.get(courseId);
        if(course != null)
            course.changeAnswerContent(user, examId, questionId, answerId, content);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }

    public void changeAnswerCorrect(User user, int courseId, int examId, int questionId, int answerId, boolean correct) {
        permissionValidator3(user);
        Course course = courses.get(courseId);
        if(course != null)
            course.changeAnswerCorrect(user, examId, questionId, answerId, correct);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }

    public void changeChatMessageContent(User user, int courseId, int chatId, int messageId, String content) {
        permissionValidator3(user);
        Course course = courses.get(courseId);
        if(course != null)
            course.changeChatMessageContent(user, chatId, messageId, content);
        else
            throw new IllegalArgumentException("Invalid Course!");
    }
}
