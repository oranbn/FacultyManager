package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class GetCourseOperation extends ClientOperation {
    private int courseId;

    public GetCourseOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            courseId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.getCourse(this);
    }
}
