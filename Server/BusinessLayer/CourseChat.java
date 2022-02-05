package BusinessLayer;

import DataAccessLayer.DTOs.DCourseChat;

import java.util.ArrayList;
import java.util.List;

public class CourseChat {
    private final int courseId;
    private String chatName;
    private final List<ChatMessage> messages;
    private String pinMessage;
    private DCourseChat dCourseChat;

    public CourseChat(int courseId, String chatName, DCourseChat courseChat) {
        this.courseId = courseId;
        this.messages = new ArrayList<>();
        this.chatName = chatName;
        this.dCourseChat = courseChat;
        this.pinMessage = null;
        courseChat.insert();
    }

   public CourseChat(DCourseChat courseChat) {
       this.messages = new ArrayList<>();
       this.courseId = courseChat.getCourseId();
       this.chatName = courseChat.getChatName();
   }
    public void addMessage(ChatMessage message)
    {
        messages.add(message);
    }
    public void removeMessage(int messageId)
    {
        messages.remove(messageId);
    }
    public void markMessage(int messageId)
    {
        messages.get(messageId).markMessage();
    }
    public void unMarkMessage(int messageId)
    {
        messages.get(messageId).unMarkMessage();
    }
    public List<ChatMessage> getMessages() {
        return messages;
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
    public void changeChatName(String chatName)
    {
        this.chatName = chatName;
        dCourseChat.setChatName(chatName);
    }
    public void pinMessage(String pinMessage)
    {
        this.pinMessage = pinMessage;
        dCourseChat.setPinMessage(pinMessage);
    }

}
