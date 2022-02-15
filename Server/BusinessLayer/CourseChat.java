package BusinessLayer;

import DataAccessLayer.DTOs.DChatMessage;
import DataAccessLayer.DTOs.DCourseChat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CourseChat {
    private final int chatId;
    private final int courseId;
    private int messageCounter;
    private String chatName;
    private final ConcurrentHashMap<Integer, ChatMessage> messages;
    private String pinMessage;
    private DCourseChat dCourseChat;

    public CourseChat(int chatId, int courseId, String chatName, DCourseChat courseChat) {
        this.chatId = chatId;
        this.courseId = courseId;
        this.messages = new ConcurrentHashMap<>();
        this.chatName = chatName;
        this.dCourseChat = courseChat;
        this.pinMessage = null;
        this.messageCounter = 0;
        courseChat.insert();
    }

   public CourseChat(DCourseChat courseChat) {
       this.messages = new ConcurrentHashMap<>();
       this.chatId = courseChat.getId();
       this.courseId = courseChat.getCourseId();
       this.chatName = courseChat.getChatName();
       this.pinMessage = courseChat.getPinMessage();
       //todo:
       //load messages
       // set message counter
   }
    public void addMessage(String userSender, String time, String content)
    {
        messages.put(messageCounter, new ChatMessage(courseId, chatId, messageCounter, userSender, time, content, new DChatMessage(chatId,courseId,messageCounter++,userSender,time,content,false)));
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
    public int getCourseId() {
        return courseId;
    }
    public ConcurrentHashMap<Integer, ChatMessage> getMessages() {
        return messages;
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

    public void deleteChat() {
        dCourseChat.delete();
        for(ChatMessage chatMessage : messages.values())
        {
            chatMessage.deleteMessage();
        }
    }
}
