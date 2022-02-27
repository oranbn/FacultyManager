package ServiceLayer.Objects;

public abstract class ServerOperation implements Operation {
    private final short opCode;

    public ServerOperation(short opCode) {
        this.opCode = opCode;

    }
    public short getOpCode() {
        return opCode;
    }
    public abstract byte[] encode();
    public int AddStringToByteArray(String str, byte[] bytes, int index) {
        for (int i = 0; i < str.length(); i++)
            bytes[index++] = (byte)str.charAt(i);
        return index;
    }
}
