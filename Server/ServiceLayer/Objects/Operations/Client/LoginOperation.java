package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class LoginOperation extends ClientOperation {
    private String userName;
    private String password;

    public LoginOperation(short opCode) {
        super(opCode);
        this.userName = "";
        this.password = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(getUserName().equals("")){
                userName = bytesToString();
            }
            else if(password.equals("")){
                this.password = bytesToString();
            }
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.login(this);
    }
}
