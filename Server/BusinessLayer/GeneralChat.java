package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class GeneralChat {
    List<ChatMessage> messages;

    public GeneralChat() {
        this.messages = new ArrayList<>();
    }

    public void loadMessages(){

    }
    public void addMessage(ChatMessage message)
    {
        messages.add(message);
    }
}
