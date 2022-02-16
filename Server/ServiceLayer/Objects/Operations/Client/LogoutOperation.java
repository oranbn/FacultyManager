package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

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

    @Override
    public void execute(Protocol protocol) {
        protocol.logout(this);
    }
}