import BusinessLayer.CourseChat;
import BusinessLayer.CourseController;
import BusinessLayer.LoggedInUsers;
import BusinessLayer.UserController;
import ServiceLayer.ConnectionsController;
import ServiceLayer.EncoderDecoder;
import ServiceLayer.Objects.Operation;
import ServiceLayer.Protocol;
import ServiceLayer.Server;

public class ServerMain {
    public static void main(String[] args){
        ConnectionsController<Operation> connectionsController = new ConnectionsController<>();
        LoggedInUsers loggedInUsers = new LoggedInUsers();
        CourseController courseController = new CourseController();
        UserController userController = new UserController(loggedInUsers);
        userController.loadData();
        courseController.loadData();
        try (Server<Operation> server = Server.reactor(Integer.parseInt(args[1]), Integer.parseInt(args[0]), () -> new Protocol(userController, courseController), EncoderDecoder::new, connectionsController);) {
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
