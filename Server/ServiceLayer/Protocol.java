package ServiceLayer;


import BusinessLayer.UserController;
import ServiceLayer.Objects.Operation;
import ServiceLayer.Objects.Operations.Client.*;
import ServiceLayer.Objects.Operations.Server.Response;

public class Protocol implements MessagingProtocol<Operation>{
    private final UserController userController;
    private String userName;
    private int connectionId;
    private Connections<Operation> connections;

    public Protocol(UserController userController) {
        this.userController = userController;
        this.userName = "";
    }

    @Override
    public void start(int connectionId, Connections<Operation> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    @Override
    public void process(Operation operation) {
        switch(operation.getOpCode()) {
            case 1:
                if (userController.register(((RegisterOperation) operation).getUserName(), ((RegisterOperation) operation).getPassword(), ((RegisterOperation) operation).getBirthday()))
                    connections.send(connectionId, new Response((short) 10, (short) 1, "Registered Successfully"));
                else
                    connections.send(connectionId, new Response((short) 11, (short) 1, "Registered Faild!"));
                break;
            case 2:
                if(userName=="")
                    if (userController.login(((LoginOperation) operation).getUserName(), ((LoginOperation) operation).getPassword(), connectionId)) {
                        connections.send(connectionId, new Response((short) 10, (short) 2, "Logged in Successfully"));
                        this.userName = ((LoginOperation) operation).getUserName();
                    }
                    else
                        connections.send(connectionId, new Response((short) 11, (short) 2, ""));
                else
                    connections.send(connectionId, new Response((short) 11, (short) 2, ""));
                break;
            case 3:
                if (userController.logout(userName))
                    connections.send(connectionId, new Response((short) 10, (short) 3, "Logged out Successfully"));
                else
                    connections.send(connectionId, new Response((short) 11, (short) 3, ""));
                break;
            case 6:
                if(userController.sendPrivateMessage(userName, (((PrivateMessageOperation)operation).getUserName()), (((PrivateMessageOperation)operation).getContent()), (((PrivateMessageOperation)operation).getDateAndTime())))
                    connections.send(connectionId, new Response((short) 10, (short) 6, "Message has been posted Successfully"));
                else
                    connections.send(connectionId, new Response((short) 11, (short) 6, ""));
                break;

        }
    }


    @Override
    public boolean shouldTerminate() {
        return false;
    }

    /*private byte[] getLogStat(byte[] ackByte, byte[] logStatByte, byte[] ageByte, byte[] numberOfPostsByte, byte[] numberOfFollowingByte, byte[] numberOfFollowersByte) {
        byte[] logStat = new byte[12];
        logStat[0] = ackByte[0];
        logStat[1] = ackByte[1];
        logStat[2] = logStatByte[0];
        logStat[3] = logStatByte[1];
        logStat[4] = ageByte[0];
        logStat[5] = ageByte[1];
        logStat[6] = numberOfPostsByte[0];
        logStat[7] = numberOfPostsByte[1];
        logStat[8] = numberOfFollowingByte[0];
        logStat[9] = numberOfFollowingByte[1];
        logStat[10] = numberOfFollowersByte[0];
        logStat[11] = numberOfFollowersByte[1];
        return logStat;
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }*/
}
