package BusinessLayer;

import java.util.concurrent.ConcurrentHashMap;

public class Course {
    private int id;
    private ConcurrentHashMap<Integer, Exam> exams;
    private ConcurrentHashMap<String, User> students;
    private ConcurrentHashMap<Integer, CourseChat> chats;
}
