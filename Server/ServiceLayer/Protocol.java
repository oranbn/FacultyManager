package ServiceLayer;


import BusinessLayer.CourseChat;
import BusinessLayer.CourseController;
import BusinessLayer.User;
import BusinessLayer.UserController;
import ServiceLayer.Objects.Operation;
import ServiceLayer.Objects.Operations.Client.*;
import ServiceLayer.Objects.Operations.Server.Response;

public class Protocol implements MessagingProtocol<Operation>{
    private final UserController userController;
    private final CourseController courseController;
    private User user;
    private int connectionId;
    private Connections<Operation> connections;

    public Protocol(UserController userController, CourseController courseController) {
        this.userController = userController;
        this.courseController = courseController;
        userController.loadData();
        courseController.loadData();
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
        try{userController.register(registerOperation.getUserName(),registerOperation.getFirstName(),registerOperation.getLastName(),registerOperation.getIdNumber(),registerOperation.getPhoneNumber(), registerOperation.getPassword(), registerOperation.getBirthday());
            connections.send(connectionId, new Response((short) 10, (short) 1, "Registered Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 11, (short) 1, e.getMessage()));
        }
    }
    public void isConnected()
    {
        if(user==null)
            throw new IllegalArgumentException("Illegal operation");
    }
    public void login(LoginOperation loginOperation) {
        if(user==null) {
            try {
                this.user = userController.login(loginOperation.getUserName(), loginOperation.getPassword(), connectionId);
                connections.send(connectionId, new Response((short) 10, (short) 2, "Logged in Successfully"));

            } catch (Exception e) {
                connections.send(connectionId, new Response((short) 11, (short) 2, e.getMessage()));
            }
        }
        else
            connections.send(connectionId, new Response((short) 11, (short) 2, "Invalid login request!"));
    }
    public void logout(LogoutOperation logoutOperation)
    {
        if(user!=null)
            try{
                userController.logout(user.getEmail());
                user = null;
                connections.send(connectionId, new Response((short)10, (short)3, "Logged out successfully"));
            }
            catch(Exception e) {

            }
    }
    public void privateMessage(PrivateMessageOperation privateMessageOperation)
    {
        try {
            userController.sendPrivateMessage(user.getEmail(), (privateMessageOperation.getUserName()), (privateMessageOperation.getContent()), (privateMessageOperation.getDateAndTime()));
            connections.send(connectionId, new Response((short) 10, (short) 6, "Message has been posted Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 11, (short) 6, ""));
        }
    }
    public void acceptFriendRequest(AcceptFriendRequestOperation acceptFriendRequestOperation){

    }
    public void addAnswer(AddAnswerOperation addAnswerOperation)
    {
        try {
            isConnected();
            courseController.addAnswer(user,addAnswerOperation.getCourseId(), addAnswerOperation.getExamId(), addAnswerOperation.getQuestionId(), addAnswerOperation.getContent(), addAnswerOperation.isCorrect());
            connections.send(connectionId, new Response((short) 10, (short) 7, "Answer added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 11, (short) 7,e.getMessage()));
        }
    }
    public void addChat(AddChatOperation addChatOperation)
    {
        try {
            isConnected();
            courseController.addChat(addChatOperation.getChatName(), addChatOperation.getCourseId());
            connections.send(connectionId, new Response((short) 10, (short) 8, "Chat added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 11, (short) 8,e.getMessage()));
        }
    }
    public void addQuestion(AddQuestionOperation addQuestionOperation)
    {
        try {
            isConnected();
            courseController.addQuestion(user, addQuestionOperation.getExamId(), addQuestionOperation.getCourseId(), addQuestionOperation.getPoints(), addQuestionOperation.getTitle(), addQuestionOperation.getAnswers());
            connections.send(connectionId, new Response((short) 10, (short) 8, "Chat added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 11, (short) 8,e.getMessage()));
        }
    }
    public void cancelFriendship(CancelFriendshipOperation cancelFriendshipOperation)
    {

    }
    public void changeAnswerContent(ChangeAnswerContentOperation changeAnswerOperation)
    {

    }
    public void changeAnswerCorrect(ChangeAnswerCorrectOperation changeAnswerOperation)
    {

    }
    public void changeChatMessage(ChangeChatMessageOperation changeChatMessageOperation)
    {

    }
    public void changeCourse(ChangeCourseNameOperation changeCourseOperation)
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
