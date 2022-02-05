package DataAccessLayer.DTOs;

import DataAccessLayer.DCourseChatController;
import DataAccessLayer.DUserController;
import DataAccessLayer.DalController;

public class DCourseChat extends DTO{
    public static final String CourseIDColumnName="CourseID";
    public static final String ChatNameColumnName="ChatName";

    private boolean persisted;
    private int courseId;
    private String chatName;

    public DCourseChat(int id, int courseId, String chatName) {
        super(new DCourseChatController(), id);
        this.courseId = courseId;
        this.chatName = chatName;
        persisted = false;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getChatName() {
        return chatName;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DCourseChatController)controller).insert(this);
            persisted = true;
        }
    }
    public void setChatName(String chatName) {
        this.chatName = chatName;
        if(persisted)
        {
            controller.update(getId(), ChatNameColumnName, chatName);
        }
    }
}
