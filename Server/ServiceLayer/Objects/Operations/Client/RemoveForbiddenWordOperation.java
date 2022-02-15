package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class RemoveForbiddenWordOperation extends ClientOperation {
    private String forbiddenWord;

    public RemoveForbiddenWordOperation(short opCode) {
        super(opCode);
        this.forbiddenWord = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            forbiddenWord = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getForbiddenWord() {
        return forbiddenWord;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.removeForbiddenWord(this);
    }
}