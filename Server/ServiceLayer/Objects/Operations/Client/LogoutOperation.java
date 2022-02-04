package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;

public class LogoutOperation extends ClientOperation {

    public LogoutOperation(short opCode) {
        super(opCode);
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        return false;
    }
}
