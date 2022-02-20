package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class LoginOperation extends ClientOperation {
    private String email;
    private String password;

    public LoginOperation(short opCode) {
        super(opCode);
        this.email = "";
        this.password = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(email.equals("")){
                email = bytesToString();
            }
            else if(password.equals("")){
                this.password = bytesToString();
            }
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.login(this);
    }
}
