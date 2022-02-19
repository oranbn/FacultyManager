package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangePasswordOperation extends ClientOperation {
    private String password;

    public ChangePasswordOperation(short opCode) {
        super(opCode);
        this.password = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
                password = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }
    public String getPassword(){ return password;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changePassword(this);
    }
}
