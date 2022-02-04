package ServiceLayer.Objects.Operations.Server;

import ServiceLayer.Objects.ServerOperation;

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
}
