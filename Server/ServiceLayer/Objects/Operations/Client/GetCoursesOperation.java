package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class GetCoursesOperation extends ClientOperation {

    public GetCoursesOperation(short opCode) {
        super(opCode);
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        return false;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.getCourses(this);
    }
}