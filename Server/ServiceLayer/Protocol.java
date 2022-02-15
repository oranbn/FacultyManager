package ServiceLayer;


import BusinessLayer.ChatMessage;
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
        operation.execute(this);
    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
    public void register(RegisterOperation registerOperation)
    {
        if (userController.register(registerOperation.getUserName(),registerOperation.getFirstName(),registerOperation.getLastName(),registerOperation.getIdNumber(),registerOperation.getPhoneNumber(), registerOperation.getPassword(), registerOperation.getBirthday()))
            connections.send(connectionId, new Response((short) 10, (short) 1, "Registered Successfully"));
        else
            connections.send(connectionId, new Response((short) 11, (short) 1, "Registered Failed!"));

    }
    public void login(LoginOperation loginOperation) {
        if(userName=="") {
            try {
                userController.login(loginOperation.getUserName(), loginOperation.getPassword(), connectionId);
                connections.send(connectionId, new Response((short) 10, (short) 2, "Logged in Successfully"));
                this.userName = loginOperation.getUserName();

            } catch (Exception e) {
                connections.send(connectionId, new Response((short) 11, (short) 2, e.getMessage()));
            }
        }
        else
            connections.send(connectionId, new Response((short) 11, (short) 2, "Invalid login request!"));
    }
    public void logout(LogoutOperation logoutOperation)
    {

    }
    public void privateMessage(PrivateMessageOperation privateMessageOperation)
    {
        if(userController.sendPrivateMessage(userName, (privateMessageOperation.getUserName()), (privateMessageOperation.getContent()), (privateMessageOperation.getDateAndTime())))
            connections.send(connectionId, new Response((short) 10, (short) 6, "Message has been posted Successfully"));
        else
            connections.send(connectionId, new Response((short) 11, (short) 6, ""));
    }
    public void acceptFriendRequest(AcceptFriendRequestOperation acceptFriendRequestOperation){

    }
    public void addAnswer(AddAnswerOperation addAnswerOperation)
    {

    }
    public void addChat(AddChatOperation addChatOperation)
    {

    }
    public void addQuestion(AddQuestionOperation addQuestionOperation)
    {

    }
    public void cancelFriendship(CancelFriendshipOperation cancelFriendshipOperation)
    {

    }
    public void changeAnswer(ChangeAnswerOperation changeAnswerOperation)
    {

    }
    public void changeChatMessage(ChangeChatMessageOperation changeChatMessageOperation)
    {

    }
    public void changeCourse(ChangeCourseOperation changeCourseOperation)
    {

    }
    public void changePassword(ChangePasswordOperation changePasswordOperation)
    {

    }
    public void changeUserPermission(ChangeUserPermissionOperation changeUserPermissionOperation)
    {

    }
    public void addChatMessage(ChatMessageOperation chatMessage)
    {

    }
    public void createCourse(CreateCourseOperation createCourseOperation)
    {

    }
    public void createExam(CreateExamOperation createExamOperation)
    {

    }
    public void createNewChat(CreateNewChatOperation createNewChatOperation)
    {

    }
    public void getAllChatMessages(GetAllChatMessagesOperation getAllChatMessagesOperation)
    {

    }
    public void getCourse(GetCourseOperation getCourseOperation)
    {

    }
    public void markMessage(MarkMessageOperation markMessageOperation)
    {

    }
    public void unMarkMessage(UnMarkMessageOperation unMarkMessageOperation)
    {

    }
    public void removeAnswer(RemoveAnswerOperation removeAnswerOperation)
    {

    }
    public void removeChatMessage(RemoveChatMessageOperation removeChatMessageOperation)
    {

    }
    public void removeChat(RemoveChatOperation removeChatOperation)
    {

    }
    public void sendFriendRequest(SendFriendRequestOperation sendFriendRequestOperation)
    {

    }
    public void addForbiddenWord(AddForbiddenWordOperation addForbiddenWordOperation)
    {

    }
    public void removeForbiddenWord(RemoveForbiddenWordOperation removeForbiddenWordOperation)
    {

    }
}
