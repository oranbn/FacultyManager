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
                if (userController.register(((RegisterOperation) operation).getUserName(),((RegisterOperation) operation).getFirstName(),((RegisterOperation) operation).getLastName(),((RegisterOperation) operation).getIdNumber(),((RegisterOperation) operation).getPhoneNumber(), ((RegisterOperation) operation).getPassword(), ((RegisterOperation) operation).getBirthday()))
                    connections.send(connectionId, new Response((short) 10, (short) 1, "Registered Successfully"));
                else
                    connections.send(connectionId, new Response((short) 11, (short) 1, "Registered Faild!"));
                break;
            case 2:
                if(userName=="") {
                    try {
                        userController.login(((LoginOperation) operation).getUserName(), ((LoginOperation) operation).getPassword(), connectionId);
                        connections.send(connectionId, new Response((short) 10, (short) 2, "Logged in Successfully"));
                        this.userName = ((LoginOperation) operation).getUserName();

                    } catch (Exception e) {
                        connections.send(connectionId, new Response((short) 11, (short) 2, e.getMessage()));
                    }
                }
                else
                    connections.send(connectionId, new Response((short) 11, (short) 2, ""));
                break;
            case 3:
                try {
                    userController.logout(userName);
                    connections.send(connectionId, new Response((short) 10, (short) 3, "Logged out Successfully"));
                } catch (Exception e) {
                    connections.send(connectionId, new Response((short) 11, (short) 3, e.getMessage()));
                }
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

}
