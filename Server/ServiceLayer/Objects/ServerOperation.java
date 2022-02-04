package ServiceLayer.Objects;

public abstract class ServerOperation implements Operation {
    private final short opCode;

    public ServerOperation(short opCode) {
        this.opCode = opCode;

    }
    public short getOpCode() {
        return opCode;
    }


}
