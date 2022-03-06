package ServiceLayer.Objects;

import java.nio.ByteBuffer;

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
    public int AddIntToByteArray(int value, byte[] bytes, int index) {
        bytes[index++] = ByteBuffer.allocate(4).putInt(value).array()[0];
        bytes[index++] = ByteBuffer.allocate(4).putInt(value).array()[1];
        bytes[index++] = ByteBuffer.allocate(4).putInt(value).array()[2];
        bytes[index++] = ByteBuffer.allocate(4).putInt(value).array()[3];
        return index;
    }
}
