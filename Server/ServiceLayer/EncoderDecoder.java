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
                        operation = new MarkMessageOperation(opCode);
                        break;
                    case 5:
                        operation = new PrivateMessageOperation(opCode);
                        break;
                    case 6:
                        operation = new RemoveAnswerOperation(opCode);
                        break;
                    case 7:
                        operation = new RemoveChatMessageOperation(opCode);
                        break;
                    case 8:
                        operation = new RemoveChatOperation(opCode);
                        break;
                    case 9:
                        operation = new RemoveForbiddenWordOperation(opCode);
                        break;
                    case 10:
                        operation = new SendFriendRequestOperation(opCode);
                        break;
                    case 11:
                        operation = new UnMarkMessageOperation(opCode);
                        break;
                    case 12:
                        operation = new AcceptFriendRequestOperation(opCode);
                        break;
                    case 13:
                        operation = new AddAnswerOperation(opCode);
                        break;
                    case 14:
                        operation = new AddChatOperation(opCode);
                        break;
                    case 15:
                        operation = new AddForbiddenWordOperation(opCode);
                        break;
                    case 16:
                        operation = new CancelFriendshipOperation(opCode);
                        break;
                    case 17:
                        operation = new ChangeAnswerContentOperation(opCode);
                        break;
                    case 18:
                        operation = new ChangeAnswerCorrectOperation(opCode);
                        break;
                    case 19:
                        operation = new ChangeChatMessageContentOperation(opCode);
                        break;
                    case 20:
                        operation = new ChangeCourseNameOperation(opCode);
                        break;
                    case 21:
                        operation = new ChangePasswordOperation(opCode);
                        break;
                    case 22:
                        operation = new ChangeQuestionTitleOperation(opCode);
                        break;
                    case 23:
                        operation = new ChangeUserPermissionOperation(opCode);
                        break;
                    case 24:
                        operation = new ChatMessageOperation(opCode);
                        break;
                    case 25:
                        operation = new CreateCourseOperation(opCode);
                        break;
                    case 26:
                        operation = new CreateExamOperation(opCode);
                        break;
                    case 27:
                        operation = new CreateNewChatOperation(opCode);
                        break;
                    case 28:
                        operation = new GetAllChatMessagesOperation(opCode);
                        break;
                    case 29:
                        operation = new ActivateAccountOperation(opCode);
                        break;
                    case 30:
                        operation = new ForgotPasswordOperation(opCode);
                        break;
                    case 31:
                        operation = new ResetPasswordOperation(opCode);
                        break;
                    case 32:
                        operation = new ForgotPasswordCodeOperation(opCode);
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
