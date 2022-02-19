package BusinessLayer;

import DataAccessLayer.DTOs.DChatMessage;
import DataAccessLayer.DTOs.DGeneralChat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GeneralChat {
    private final ConcurrentHashMap<Integer, ChatMessage> messages;
    List<String> forbiddenWords;
    private int messageId = 0;
    private ChatMessage pinMessage;
    private boolean blockChat;

    public GeneralChat() {
        this.messages = new ConcurrentHashMap<>();
        forbiddenWords = new ArrayList<>();
        blockChat = false;
    }
    public GeneralChat(DGeneralChat dGeneralChat)
    {
        this.messages = new ConcurrentHashMap<>();
    }

    public void addMessage(String userSender, String time, String content)
    {
        messages.put(messageId, new ChatMessage(-1,-1,messageId,userSender,time, content, new DChatMessage(0, 0,messageId++, userSender, time, content, false)));
    }
    public void removeMessage(int messageId) {
    ChatMessage message = messages.get(messageId);
    if(message!=null)
    {
        message.deleteMessage();
        messages.remove(messageId);
    }
    }
    public void blockChat(){
        blockChat = true;
    }
    public void unBlockChat()
    {
        blockChat = false;
    }
    public ChatMessage getPinMessage()
    {
        return pinMessage;
    }
    public void setPinMessage(int messageId)
    {
        if(messages.containsKey(messageId)) {
            pinMessage = messages.get(messageId);
            // update db
            // update client
        }
    }

    public void markMessage(int messageId) {
        if(messages.containsKey(messageId))
            messages.get(messageId).markMessage();
        else
            throw new IllegalArgumentException("Message not found!");
    }

    public void unMarkMessage(int messageId) {
        if(messages.containsKey(messageId))
            messages.get(messageId).unMarkMessage();
        else
            throw new IllegalArgumentException("Message not found!");
    }
}
