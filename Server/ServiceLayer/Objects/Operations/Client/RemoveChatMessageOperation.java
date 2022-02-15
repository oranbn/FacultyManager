package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class RemoveChatMessageOperation extends ClientOperation {
    private int courseId;
    private int chatId;
    private int messageId;

    public RemoveChatMessageOperation(short opCode) {
        super(opCode);
        courseId = -1;
        chatId = -1;
        messageId =  -1;
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
            else
                messageId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getMessageId() {
        return messageId;
    }
    public int getCourseId(){
        return courseId;
    }
    public int getChatId(){return chatId;}

    @Override
    public void execute(Protocol protocol) {
        protocol.removeChatMessage(this);
    }
}
