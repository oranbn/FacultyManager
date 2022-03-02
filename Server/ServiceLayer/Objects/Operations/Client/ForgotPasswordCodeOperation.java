package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ForgotPasswordCodeOperation extends ClientOperation {
    private String forgotPasswordCode;
    private String email;

    public ForgotPasswordCodeOperation(short opCode) {
        super(opCode);
        this.forgotPasswordCode = "";
        this.email = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(forgotPasswordCode.equals(""))
                forgotPasswordCode = bytesToString();
            else
                email = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getForgotPasswordCode() {
        return forgotPasswordCode;
    }
    public String getEmail() { return email; }

    @Override
    public void execute(Protocol protocol) {
        protocol.forgotPasswordCode(this);
    }
}