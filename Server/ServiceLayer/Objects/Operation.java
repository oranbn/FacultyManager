package ServiceLayer.Objects;

import ServiceLayer.Protocol;

public interface Operation {
    public short getOpCode();
    public void execute(Protocol protocol);
    public byte[] encode();
}

