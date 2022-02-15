package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangeUserPermissionOperation extends ClientOperation {
    private String username;
    private int permission;

    public ChangeUserPermissionOperation(short opCode) {
        super(opCode);
        this.username = "";
        this.permission = -1;
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
                permission = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getUsername() { return username; }
    public int getPermission(){ return permission;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changeUserPermission(this);
    }
}
