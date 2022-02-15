package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class CreateNewChatOperation extends ClientOperation {
    private int courseId;
    String chatName;

    public CreateNewChatOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        chatName = "";
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
                chatName = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public String getChatName(){ return chatName; }

    @Override
    public void execute(Protocol protocol) {
        protocol.createNewChat(this);
    }
}
