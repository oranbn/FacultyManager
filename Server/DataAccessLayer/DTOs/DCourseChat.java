package DataAccessLayer.DTOs;

import DataAccessLayer.DChatMessageController;
import DataAccessLayer.DCourseChatController;
import DataAccessLayer.DUserController;
import DataAccessLayer.DalController;

public class DCourseChat extends DTO{
    public static final String CourseIDColumnName="CourseID";
    public static final String ChatNameColumnName="ChatName";
    public static final String PinMessageColumnName="PinMessage";

    private boolean persisted;
    private int courseId;
    private String chatName;
    private String pinMessage;

    public DCourseChat(int id, int courseId, String chatName, String pinMessage) {
        super(new DCourseChatController(), id);
        this.courseId = courseId;
        this.chatName = chatName;
        this.pinMessage = pinMessage;
        persisted = false;
    }

    public int getCourseId() {
        return courseId;
    }
    public String getPinMessage() {
        return pinMessage;
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
    public void setPinMessage(String pinMessage) {
        this.pinMessage = pinMessage;
        if(persisted)
        {
            controller.update(getId(), PinMessageColumnName, pinMessage);
        }
    }
    public void delete() {
        if(persisted)
        {
            ((DCourseChatController)controller).delete(this);
        }
    }
}
