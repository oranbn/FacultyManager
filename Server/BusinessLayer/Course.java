package BusinessLayer;

import DataAccessLayer.DTOs.DCourse;
import DataAccessLayer.DTOs.DCourseChat;
import DataAccessLayer.DTOs.DExam;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Course {
    private int id;
    private int courseChatCounter;
    private int examCounter;
    private String name;
    private String generalInfo;
    private final List<String> teachers;
    private final ConcurrentHashMap<Integer, Exam> exams;
    private final ConcurrentHashMap<Integer, Homework> homeworks;
    private final ConcurrentHashMap<String, User> students;
    private final ConcurrentHashMap<Integer, CourseChat> chats;
    private final DCourse dCourse;
    public Course(int id, String name, String generalInfo, DCourse dCourse) {
        this.id = id;
        this.name = name;
        this.generalInfo = generalInfo;
        this.teachers = new ArrayList<>();
        this.exams = new ConcurrentHashMap<>();
        this.homeworks = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.chats = new ConcurrentHashMap<>();
        this.courseChatCounter = 0;
        this.examCounter = 0;
        this.dCourse = dCourse;
    }
    public Course(DCourse dCourse)
    {
        this.dCourse = dCourse;
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
        // load course chat counter and exam counter
    }
    public void addTeacher(String teacher)
    {
        teachers.add(teacher);
    }
    public void addMessage(String email, int chatId, String content, String time){
        CourseChat chat = chats.get(chatId);
        if(chat == null)
            throw new IllegalArgumentException("Invalid chat!");
        chat.addMessage(email,content, time);
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
    public void addChat(String chatName)
    {
        chats.put(courseChatCounter, new CourseChat(courseChatCounter, id, chatName, new DCourseChat(courseChatCounter++, id, chatName, "")));
    }
    public void removeChat(int chatId)
    {
        if(!chats.contains(chatId))
            throw new IllegalArgumentException("Invalid chat!");
            chats.remove(chatId).deleteChat();
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

    public void setName(String name) {
        this.name = name;
        dCourse.setName(name);
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
        dCourse.setGeneralInfo(generalInfo);
    }

    public void addAnswer(int examId, int questionId, String content, boolean correct) {
    }

    public void addQuestion( int examId, double points, String title, List<PreExamAnswer> answers) {
        Exam exam = exams.get(examId);
        if(exam!=null)
            exam.addQuestion(points,title,answers);
        else
            throw new IllegalArgumentException("Exam not found!");
    }

    public void markMessage(int chatId, int messageId) {
        CourseChat chat = chats.get(chatId);
        if(chat!=null)
            chat.markMessage(messageId);
        else
            throw new IllegalArgumentException("Chat not found!");
    }

    public void changeAnswerContent(User user, int examId, int questionId, int answerId, String content) {
        Exam exam = exams.get(examId);
        if(exam!=null)
            exam.changeAnswerContent(questionId, answerId, content);
        else
            throw new IllegalArgumentException("Exam not found!");
    }

    public void changeAnswerCorrect(User user, int examId, int questionId, int answerId, boolean correct) {
        Exam exam = exams.get(examId);
        if(exam!=null)
            exam.changeAnswerCorrect(questionId, answerId, correct);
        else
            throw new IllegalArgumentException("Exam not found!");
    }

    public void changeChatMessageContent(User user, int chatId, int messageId, String content) {
        CourseChat chat = chats.get(chatId);
        if(chat!=null)
            chat.changeChatMessageContent(messageId, content);
        else
            throw new IllegalArgumentException("Chat not found!");
    }

    public void addExam(User user, int duration, String examDate) {
        //todo:
        // check if examDate is valid
        exams.put(examCounter, new Exam(id, examCounter, duration, examDate, new DExam(examCounter++, id, duration, false, examDate)));
    }

    public void removeAnswer(int examId, int questionId, int answerId) {
        Exam exam = exams.get(examId);
        if(exam!=null)
            exam.removeAnswer(questionId, answerId);
        else
            throw new IllegalArgumentException("Exam not found!");
    }

    public void removeChatMessage(String email, int chatId, int messageId) {
        CourseChat chat = chats.get(chatId);
        if(chat!=null)
            chat.removeMessage(messageId);
        else
            throw new IllegalArgumentException("Chat not found!");
    }
}
