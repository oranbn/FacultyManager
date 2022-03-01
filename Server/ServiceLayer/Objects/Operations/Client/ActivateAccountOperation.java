package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ActivateAccountOperation extends ClientOperation {
    private String activationCode;

    public ActivateAccountOperation(short opCode) {
        super(opCode);
        this.activationCode = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            activationCode = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getActivationCode() {
        return activationCode;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.activateAccount(this);
    }
}
