package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class UnMarkMessageOperation extends ClientOperation {
    private int courseId;
    private int chatId;
    private int messageId;

    public UnMarkMessageOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        this.chatId = -1;
        this.messageId = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(courseId==-1)
                courseId = bytesToInt();
            else if(chatId == -1)
                chatId = bytesToInt();
            else
                messageId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }
    public int getChatId(){return courseId;}
    public int getCourseId(){return courseId;}
    public int getMessageId() {
        return messageId;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.unMarkMessage(this);
    }
}
