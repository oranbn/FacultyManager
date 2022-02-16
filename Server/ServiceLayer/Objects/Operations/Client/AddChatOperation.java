package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class AddChatOperation extends ClientOperation {
    private int courseId;
    private String chatName;

    public AddChatOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        this.chatName = "";
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

    public String getChatName() { return chatName; }
    public int getCourseId(){ return courseId;}

    @Override
    public void execute(Protocol protocol) {
        protocol.addChat(this);
    }
}
