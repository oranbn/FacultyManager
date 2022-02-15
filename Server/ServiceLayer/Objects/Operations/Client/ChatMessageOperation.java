package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChatMessageOperation extends ClientOperation {
    private int courseId;
    private int chatId;
    private int messageId;
    private String time;
    private String content;

    public ChatMessageOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        this.chatId = -1;
        this.messageId = -1;
        this.time = "";
        this.content = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(courseId==-1)
                courseId = bytesToInt();
            else if(chatId==-1)
                chatId = bytesToInt();
            else if(messageId==-1)
                messageId = bytesToInt();
            else if(time.equals(""))
                time = bytesToString();
            else
                content = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public int getChatId(){ return chatId;}
    public int getMessageId(){return messageId;}
    public String getTime(){return time;}
    public String getContent(){ return content;}

    @Override
    public void execute(Protocol protocol) {
        protocol.addChatMessage(this);
    }
}
