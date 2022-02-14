package DataAccessLayer.DTOs;

import DataAccessLayer.DChatMessageController;
import DataAccessLayer.DalController;

public class DChatMessage extends DTO {
    public static final String CourseIdColumnName="CourseId";
    public static final String MessageIdColumnName="MessageId";
    public static final String UserSenderColumnName="User";
    public static final String TimeColumnName="Time";
    public static final String ContentColumnName="Content";
    public static final String MarkColumnName="Mark";
    private boolean persisted;
    private int courseId;
    private int messageId;
    private String userSender;
    private String time;
    private String content;
    private boolean mark;

    public DChatMessage(int id, int courseId, int messageId, String userSender, String time, String content, boolean mark) {
        super(new DChatMessageController(), id);
        this.courseId = courseId;
        this.userSender = userSender;
        this.messageId = messageId;
        this.time = time;
        this.content = content;
        this.mark = mark;
        persisted = false;
    }
    public int getCourseId() { return courseId; }
    public int getMessageId() { return messageId; }
    public String getUserSender() {
        return userSender;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public boolean isMark() {
        return mark;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DChatMessageController)controller).insert(this);
            persisted = true;
        }
    }
    public void setMark(Boolean mark) {
        this.mark = mark;
        if(persisted)
        {
            controller.update(getId(), MarkColumnName, mark);
        }
    }

    public void delete() {
        if(persisted)
        {
            ((DChatMessageController)controller).delete(this);
        }
    }
}
