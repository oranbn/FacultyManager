package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangeUserPermissionOperation extends ClientOperation {
    private String email;
    private int permission;

    public ChangeUserPermissionOperation(short opCode) {
        super(opCode);
        this.email = "";
        this.permission = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(email.equals(""))
                email = bytesToString();
            else
                permission = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getEmail() { return email; }
    public int getPermission(){ return permission;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changeUserPermission(this);
    }
}
