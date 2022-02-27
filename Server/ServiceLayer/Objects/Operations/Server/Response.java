package ServiceLayer.Objects.Operations.Server;

import ServiceLayer.Objects.ServerOperation;
import ServiceLayer.Protocol;

public class Response extends ServerOperation {
    private short messageOpCode;
    private String optional;
    public Response(short opCode, short messageOpCode, String optional) {
        super(opCode);
        this.messageOpCode = messageOpCode;
        this.optional = optional;
    }
    public short getMessageOpCode() {
        return messageOpCode;
    }
    public String getOptional() {
        return optional;
    }

    @Override
    public void execute(Protocol protocol) {
    }
    public byte[] encode() {
        byte[] bytes = new byte[optional.length() + 6];
        bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
        bytes[1] = (byte)(getOpCode() & 0xFF);
        bytes[2] = (byte)((messageOpCode >> 8) & 0xFF);
        bytes[3] = (byte)(messageOpCode & 0xFF);
        int index = 4;
        index = AddStringToByteArray(optional, bytes, index);
        bytes[index++] = 0;
        bytes[index] = (byte)';';
        return bytes;
    }
}
