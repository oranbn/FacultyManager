package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class MarkMessageOperation extends ClientOperation {
    private int messageId;

    public MarkMessageOperation(short opCode) {
        super(opCode);
        this.messageId = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            messageId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getMessageId() {
        return messageId;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.markMessage(this);
    }
}
