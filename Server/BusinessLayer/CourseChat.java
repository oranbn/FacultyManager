package BusinessLayer;

import DataAccessLayer.DTOs.DChatMessage;

import java.util.ArrayList;
import java.util.List;

public class CourseChat {
    private int courseId;
    List<ChatMessage> messages;

    public CourseChat(int courseId) {
        this.courseId = courseId;
        this.messages = new ArrayList<>();
    }

    public void loadMessages(List<DChatMessage> messages){
        for(DChatMessage message: messages){
            //messages.add(new ChatMessage(message.getChatId))
        }

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

}
