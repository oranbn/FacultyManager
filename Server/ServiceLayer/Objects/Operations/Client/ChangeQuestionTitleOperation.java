package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangeQuestionTitleOperation extends ClientOperation {
    public ChangeQuestionTitleOperation(short opCode) {
        super(opCode);
    }

    @Override
    public boolean pushByte(byte nextByte) {
        return false;
    }

    @Override
    public void execute(Protocol protocol) {

    }
}
