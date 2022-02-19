package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class AcceptFriendRequestOperation extends ClientOperation {
    private String email;

    public AcceptFriendRequestOperation(short opCode) {
        super(opCode);
        this.email = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            email = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.acceptFriendRequest(this);
    }
}
