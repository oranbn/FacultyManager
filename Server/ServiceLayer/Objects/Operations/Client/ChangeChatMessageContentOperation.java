package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangeChatMessageContentOperation extends ClientOperation {
    private int messageId;
    private int chatId;
    private int courseId;
    private String content;

    public ChangeChatMessageContentOperation(short opCode) {
        super(opCode);
        this.messageId =-1;
        this.chatId = -1;
        this.courseId = -1;
        this.content = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(messageId==-1)
                messageId = bytesToInt();
            else if(chatId==-1)
                chatId = bytesToInt();
            else if(courseId==-1)
                courseId = bytesToInt();
            else
                content = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }
    public int getMessageId(){return messageId;}
    public int getChatId(){return chatId;}
    public int getCourseId(){ return courseId;}
    public String getContent(){return content;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changeChatMessageContent(this);
    }
}
