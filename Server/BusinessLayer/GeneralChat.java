package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class GeneralChat {
    private List<ChatMessage> messages;
    private String pinMessage;
    private boolean blockChat;

    public GeneralChat() {
        this.messages = new ArrayList<>();
        blockChat = false;

    }

    public void addMessage(ChatMessage message)
    {
        messages.add(message);
    }
    public void removeMessage(ChatMessage message) {messages.remove(message);}
    public void blockChat(){
        blockChat = true;
    }
    public void unBlockChat()
    {
        blockChat = false;
    }
}
