package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class SendFriendRequestOperation extends ClientOperation {
    private String username;

    public SendFriendRequestOperation(short opCode) {
        super(opCode);
        this.username = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            username = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.sendFriendRequest(this);
    }
}
