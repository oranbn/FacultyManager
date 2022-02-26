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
        short opCode = message.getOpCode();
        byte[] bArrOpCode = shortToBytes(opCode);
        byte[] bArrMessage;
        switch (opCode)
        {
            /*case 9:
                byte notificationByte = ((NotificationOperation)message).getNotificationType();
                byte[] bArrPostingUser = ((NotificationOperation)message).getPostingUser().getBytes();
                byte[] bArrContent = ((NotificationOperation)message).getContent().getBytes();
                byte[] bArrNotification = new byte[6+bArrPostingUser.length+bArrContent.length];
                bArrNotification[0] = bArrOpCode[0];
                bArrNotification[1] = bArrOpCode[1];
                bArrNotification[2] = notificationByte;
                for(int i=0;i<bArrPostingUser.length;i++)
                    bArrNotification[i+3] = bArrPostingUser[i];
                bArrNotification[3+bArrPostingUser.length] = (byte)0;
                for(int i=0;i<bArrContent.length;i++)
                    bArrNotification[i+4+bArrPostingUser.length] = bArrContent[i];
                bArrNotification[bArrNotification.length-2] = (byte)0;
                bArrNotification[bArrNotification.length-1] = ';';
                return bArrNotification;*/

            case 1:
                bArrMessage = shortToBytes(((Response)message).getMessageOpCode());
                byte[] bArrOptional = ((Response)message).getOptional().getBytes();
                byte[] bArrACK = new byte[6+bArrOptional.length];
                bArrACK[0] = bArrOpCode[0];
                bArrACK[1] = bArrOpCode[1];
                bArrACK[2] = bArrMessage[0];
                bArrACK[3] = bArrMessage[1];
                for(int i=0;i<bArrOptional.length;i++)
                    bArrACK[i+4] = bArrOptional[i];
                bArrACK[bArrACK.length-2] = 0;
                bArrACK[bArrACK.length-1] = ';';
                return bArrACK;

            case 2:
                bArrMessage = shortToBytes(((Response)message).getMessageOpCode());
                bArrOptional = ((Response)message).getOptional().getBytes();
                byte[] bArrErr = new byte[6+ bArrOptional.length];
                bArrErr[0] = bArrOpCode[0];
                bArrErr[1] = bArrOpCode[1];
                bArrErr[2] = bArrMessage[0];
                bArrErr[3] = bArrMessage[1];
                for(int i=0;i<bArrOptional.length;i++)
                    bArrErr[i+4] = bArrOptional[i];
                bArrErr[bArrErr.length-2] = 0;
                bArrErr[bArrErr.length-1] = ';';
                return bArrErr;
        }
        return null;
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
