package BusinessLayer;

import DataAccessLayer.DTOs.DCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Course {
    private int id;
    private String name;
    private String generalInfo;
    private final List<String> teachers;
    private final ConcurrentHashMap<Integer, Exam> exams;
    private final ConcurrentHashMap<Integer, Homework> homeworks;
    private final ConcurrentHashMap<String, User> students;
    private final ConcurrentHashMap<Integer, CourseChat> chats;

    public Course(int id, String name, String generalInfo) {
        this.id = id;
        this.name = name;
        this.generalInfo = generalInfo;
        this.teachers = new ArrayList<>();
        this.exams = new ConcurrentHashMap<>();
        this.homeworks = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.chats = new ConcurrentHashMap<>();
    }
    public Course(DCourse dCourse)
    {
        this.id = dCourse.getId();
        this.name = dCourse.getName();
        this.generalInfo = dCourse.getGeneralInfo();
        this.teachers = new ArrayList<>();
        this.exams = new ConcurrentHashMap<>();
        this.homeworks = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.chats = new ConcurrentHashMap<>();
        //todo
        // load teachers, exams, students and chats:
    }
    public void addTeacher(String teacher)
    {
        teachers.add(teacher);
    }
    public void removeTeacher(String teacher)
    {
        teachers.remove(teacher);
    }
    public void addExam(Exam exam, int examId)
    {
        exams.put(examId,exam);
    }
    public void removeExam(int examId)
    {
        exams.remove(examId);
    }
    public void addHomework(Homework homework, int homeworkId)
    {
        homeworks.put(homeworkId,homework);
    }
    public void removeHomework(int homeworkId)
    {
        homeworks.remove(homeworkId);
    }
    public void addStudent(User user)
    {
        students.put(user.getEmail(), user);
    }
    public void removeStudent(String email)
    {
        students.remove(email);
    }
    public void addChat(CourseChat courseChat, int chatId)
    {
        chats.put(chatId, courseChat);
    }
    public void removeChat(int chatId)
    {
        chats.remove(chatId);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public ConcurrentHashMap<Integer, Exam> getExams() {
        return exams;
    }

    public ConcurrentHashMap<Integer, Homework> getHomeworks() {
        return homeworks;
    }

    public ConcurrentHashMap<String, User> getStudents() {
        return students;
    }

    public ConcurrentHashMap<Integer, CourseChat> getChats() {
        return chats;
    }
}
