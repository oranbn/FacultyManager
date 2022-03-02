package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ResetPasswordOperation extends ClientOperation {
    private String newPassword;
    private String email;

    public ResetPasswordOperation(short opCode) {
        super(opCode);
        this.newPassword = "";
        this.email = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(newPassword.equals(""))
                newPassword = bytesToString();
            else
                email = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public String getEmail() { return email; }

    @Override
    public void execute(Protocol protocol) {
        protocol.resetPassword(this);
    }
}