import BusinessLayer.UserController;
import ServiceLayer.ConnectionsController;
import ServiceLayer.Protocol;
import ServiceLayer.Server;

public class ServerMain {
   /* public static void main(String[] args){
        ConnectionsController<Operation> connectionsController = new ConnectionsController<>();
        UserController userController = new UserController(connectionsController);
        try (Server<Operation> server = Server.reactor(Integer.parseInt(args[1]), Integer.parseInt(args[0]), () -> new Protocol(userController), OperationEncoderDecoder::new, connectionsController);) {
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
