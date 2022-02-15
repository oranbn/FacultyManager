package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangePasswordOperation extends ClientOperation {
    private String username;
    private String password;

    public ChangePasswordOperation(short opCode) {
        super(opCode);
        this.username = "";
        this.password = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(username.equals(""))
                username = bytesToString();
            else
                password = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getUsername() { return username; }
    public String getPassword(){ return password;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changePassword(this);
    }
}
