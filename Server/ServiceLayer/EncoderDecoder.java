package ServiceLayer;


import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Objects.Operation;
import ServiceLayer.Objects.Operations.Client.*;
import ServiceLayer.Objects.Operations.Server.Response;

import java.util.Arrays;

public class EncoderDecoder implements MessageEncoderDecoder<Operation> {
    int length = 0;
    private byte[] bytes = new byte[1 << 10];
    short opCode=-1;
    ClientOperation operation=null;
    @Override
    public Operation decodeNextByte(byte nextByte) {
        if (opCode==-1)
        {
            pushByte(nextByte);
            if(length==2) {
                opCode = bytesToShort(Arrays.copyOfRange(bytes, 0, 2));
                switch(opCode) {
                    case 1:
                        operation = new RegisterOperation(opCode);
                        break;
                    case 2:
                        operation = new LoginOperation(opCode);
                        break;
                    case 3:
                        operation = new LogoutOperation(opCode);
                        break;
                    case 4:
                        operation = new PrivateMessageOperation(opCode);
                        break;
                }
            }
        }
        else
        {
            if(operation.pushByte(nextByte)) {
                opCode = -1;
                length = 0;
                ClientOperation op = operation;
                operation = null;
                return op;
            }
        }
        return null;
    }

    private void pushByte(byte nextByte) {
        if (length >= bytes.length) {
            bytes = Arrays.copyOf(bytes, length * 2);
        }
        bytes[length++] = nextByte;
    }
    @Override
    public byte[] encode(Operation message) {
        return message.encode();
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
}
