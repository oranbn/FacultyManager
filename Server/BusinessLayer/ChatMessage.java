package BusinessLayer;

import DataAccessLayer.DTOs.DChatMessage;

import java.util.List;

public class ChatMessage {
    private final int courseId;
    private final int chatId;
    private final int messageId;
    private final String userSender;
    private final String time;
    private String content;
    private boolean mark;
    private final DChatMessage dChatMessage;

    public ChatMessage(int courseId, int chatId,int messageId, String userSender, String time, String content, DChatMessage dChatMessage) {
        this.courseId = courseId;
        this.chatId = chatId;
        this.messageId = messageId;
        this.userSender = userSender;
        this.time = time;
        List<String> forbiddenWords = ForbiddenWords.getInstance().getForbiddenWords();
        for(int i=0; i<forbiddenWords.size();i++)
            content = content.replaceAll(forbiddenWords.get(i), "<filtered>");
        this.content = content;
        this.mark = false;
        this.dChatMessage = dChatMessage;
        dChatMessage.insert();
    }
    public ChatMessage(DChatMessage dChatMessage)
    {
        courseId = dChatMessage.getCourseId();;
        chatId = dChatMessage.getId();
        messageId = dChatMessage.getMessageId();
        userSender = dChatMessage.getUserSender();
        time = dChatMessage.getTime();
        content = dChatMessage.getContent();
        this.dChatMessage = dChatMessage;
    }
    public void markMessage()
    {
        mark = true;
        dChatMessage.setMark(true);
    }
    public void unMarkMessage()
    {
        mark = false;
        dChatMessage.setMark(false);
    }
    public boolean isMark() {
        return mark;
    }
    public int getChatId() {
        return chatId;
    }

    public String getUserSender() {
        return userSender;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void deleteMessage()
    {
        dChatMessage.delete();
    }

    public void changeChatMessageContent(String content) {
        this.content = content;
    }
}
