package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class RemoveChatOperation extends ClientOperation {
    private int courseId;
    private int chatId;

    public RemoveChatOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        this.chatId = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(courseId==-1)
                courseId = bytesToInt();
            else
                chatId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getChatId() { return chatId; }
    public int getCourseId(){ return courseId;}

    @Override
    public void execute(Protocol protocol) {
        protocol.removeChat(this);
    }
}
