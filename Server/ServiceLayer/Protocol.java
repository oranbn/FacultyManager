package ServiceLayer;


import BusinessLayer.*;
import ServiceLayer.Objects.Operation;
import ServiceLayer.Objects.Operations.Client.*;
import ServiceLayer.Objects.Operations.Server.AccountResponse;
import ServiceLayer.Objects.Operations.Server.CoursesResponse;
import ServiceLayer.Objects.Operations.Server.Response;

import java.util.List;

public class Protocol implements MessagingProtocol<Operation>{
    private final UserController userController;
    private final CourseController courseController;
    private User user;
    private int connectionId;
    private Connections<Operation> connections;

    public Protocol(UserController userController, CourseController courseController) {
        this.userController = userController;
        this.courseController = courseController;
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
    public void logout(){
        try {
            userController.logout(user.getEmail());
        } catch (Exception e) {
        }
        finally {
            user = null;
        }
    }
    public void register(RegisterOperation registerOperation) {
        try{userController.register(registerOperation.getEmail(),registerOperation.getFirstName(),registerOperation.getLastName(),registerOperation.getIdNumber(),registerOperation.getPhoneNumber(), registerOperation.getPassword(), registerOperation.getBirthday());
            connections.send(connectionId, new Response((short) 1, (short) 1, "Registered Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 1, e.getMessage()));
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
                this.user = userController.login(loginOperation.getEmail(), loginOperation.getPassword(), connectionId);
                connections.send(connectionId, new AccountResponse((short)3, user.getEmail(), user.getFirstName(), user.getLastName(), user.getIdNumber(), user.getPhoneNumber(), user.getBirthday(), user.getPermissionLevel(), user.isEmailApproved()));

            } catch (Exception e) {
                connections.send(connectionId, new Response((short) 2, (short) 2, e.getMessage()));
            }
        }
        else
            connections.send(connectionId, new Response((short) 2, (short) 2, "Invalid login request!"));
    }
    public void logout(LogoutOperation logoutOperation) {
        if(user!=null)
            try{
                userController.logout(user.getEmail());
                user = null;
                connections.send(connectionId, new Response((short)1, (short)3, "Logged out successfully"));
            }
            catch(Exception e) {
                connections.send(connectionId, new Response((short)2, (short)3, e.getMessage()));
            }
    }
    public void privateMessage(PrivateMessageOperation privateMessageOperation) {
        try {
            userController.sendPrivateMessage(user.getEmail(), (privateMessageOperation.getUserName()), (privateMessageOperation.getContent()), (privateMessageOperation.getDateAndTime()));
            connections.send(connectionId, new Response((short) 1, (short) 6, "Message has been posted Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 6, ""));
        }
    }
    public void activateAccount(ActivateAccountOperation activateAccountOperation) {
        if(user!=null)
            try{
                userController.activateAccount(user.getEmail(), activateAccountOperation.getActivationCode());
                connections.send(connectionId, new Response((short)1, (short)29, "Activated account successfully"));
            }
            catch(Exception e) {
                connections.send(connectionId, new Response((short)2, (short)29, e.getMessage()));
            }
    }
    public void acceptFriendRequest(AcceptFriendRequestOperation acceptFriendRequestOperation){
        try{
            isConnected();
            userController.acceptFriendRequest(user.getEmail(),acceptFriendRequestOperation.getEmail());
        }
        catch (Exception e)
        {

        }
    }
    public void addAnswer(AddAnswerOperation addAnswerOperation) {
        try {
            isConnected();
            courseController.addAnswer(user,addAnswerOperation.getCourseId(), addAnswerOperation.getExamId(), addAnswerOperation.getQuestionId(), addAnswerOperation.getContent(), addAnswerOperation.isCorrect());
            connections.send(connectionId, new Response((short) 1, (short) 7, "Answer added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 7,e.getMessage()));
        }
    }
    public void addChat(AddChatOperation addChatOperation) {
        try {
            isConnected();
            courseController.addChat(user, addChatOperation.getChatName(), addChatOperation.getCourseId());
            connections.send(connectionId, new Response((short) 1, (short) 8, "Chat added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 8,e.getMessage()));
        }
    }
    public void addQuestion(AddQuestionOperation addQuestionOperation) {
        try {
            isConnected();
            courseController.addQuestion(user, addQuestionOperation.getExamId(), addQuestionOperation.getCourseId(), addQuestionOperation.getPoints(), addQuestionOperation.getTitle(), addQuestionOperation.getAnswers());
            connections.send(connectionId, new Response((short) 1, (short) 8, "Chat added Successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 8,e.getMessage()));
        }
    }
    public void cancelFriendship(CancelFriendshipOperation cancelFriendshipOperation) {
        try{
            isConnected();
            userController.cancelFriendship(user.getEmail(),cancelFriendshipOperation.getEmail());
        }
        catch (Exception e) {

        }
    }
    public void changeAnswerContent(ChangeAnswerContentOperation changeAnswerOperation) {
        try{
            isConnected();
            courseController.changeAnswerContent(user, changeAnswerOperation.getCourseId(), changeAnswerOperation.getExamId(), changeAnswerOperation.getQuestionId(), changeAnswerOperation.getAnswerId(), changeAnswerOperation.getContent());
        }
        catch (Exception e) {

        }
    }
    public void changeAnswerCorrect(ChangeAnswerCorrectOperation changeAnswerOperation) {
        try{
            isConnected();
            courseController.changeAnswerCorrect(user, changeAnswerOperation.getCourseId(), changeAnswerOperation.getExamId(), changeAnswerOperation.getQuestionId(), changeAnswerOperation.getAnswerId(), changeAnswerOperation.getCorrect());
        }
        catch (Exception e) {

        }
    }
    public void changeChatMessageContent(ChangeChatMessageContentOperation changeChatMessageContentOperation) {
        try{
            isConnected();
            courseController.changeChatMessageContent(user, changeChatMessageContentOperation.getCourseId(), changeChatMessageContentOperation.getChatId(), changeChatMessageContentOperation.getMessageId(), changeChatMessageContentOperation.getContent());
        }
        catch (Exception e) {

        }
    }
    public void changeCourse(ChangeCourseNameOperation changeCourseOperation)
    {

    }
    public void changePassword(ChangePasswordOperation changePasswordOperation)
    {
        try{
            isConnected();
            userController.changePassword(user.getEmail(), changePasswordOperation.getPassword());
            connections.send(connectionId, new Response((short) 1, (short) 21, ""));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 21, e.getMessage()));
        }
    }
    public void changeUserPermission(ChangeUserPermissionOperation changeUserPermissionOperation)
    {
        try{
            isConnected();
            userController.changeUserPermission(user, changeUserPermissionOperation.getEmail(), changeUserPermissionOperation.getPermission());
            connections.send(connectionId, new Response((short) 1, (short) 23, ""));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 23, e.getMessage()));
        }
    }
    public void addChatMessage(ChatMessageOperation chatMessage)
    {
        try{
            isConnected();
            courseController.addChatMessage(user, chatMessage.getCourseId(), chatMessage.getChatId(), chatMessage.getContent(), chatMessage.getTime());
            connections.send(connectionId, new Response((short) 1, (short) 24, ""));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 24, e.getMessage()));
        }
    }
    public void createCourse(CreateCourseOperation createCourseOperation)
    {
        try{
            isConnected();
            courseController.addCourse(user, createCourseOperation.getName(), createCourseOperation.getGeneralInfo());
            connections.send(connectionId, new Response((short) 1, (short) 25, ""));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 25, e.getMessage()));
        }
    }
    public void createExam(CreateExamOperation createExamOperation)
    {
        try{
            isConnected();
            courseController.addExam(user, createExamOperation.getCourseId(), createExamOperation.getDuration(), createExamOperation.getExamDate());
        }
        catch (Exception e) {

        }
    }
    public void createNewChat(CreateNewChatOperation createNewChatOperation)
    {
        try{
            isConnected();
            courseController.addChat(user, createNewChatOperation.getChatName(), createNewChatOperation.getCourseId());
        }
        catch (Exception e) {

        }
    }
    public void getAllChatMessages(GetAllChatMessagesOperation getAllChatMessagesOperation)
    {

    }
    public void getCourses(GetCoursesOperation getCourseOperation)
    {
        try{
            isConnected();
            List<Course> courseList = courseController.getCourses(user.getEmail(), user.getPermissionLevel());
            connections.send(connectionId, new CoursesResponse((short) 4, courseList));
        }
        catch (Exception e)
        {
            connections.send(connectionId, new Response((short) 2, (short) 33, e.getMessage()));

        }
    }
    public void markMessage(MarkMessageOperation markMessageOperation)
    {
        try{
            isConnected();
            courseController.markMessage(markMessageOperation.getCourseId(), markMessageOperation.getChatId(), markMessageOperation.getMessageId());
        }
        catch (Exception e)
        {

        }
    }
    public void unMarkMessage(UnMarkMessageOperation unMarkMessageOperation)
    {
        try{
            isConnected();
            courseController.unMarkMessage(unMarkMessageOperation.getCourseId(), unMarkMessageOperation.getChatId(), unMarkMessageOperation.getMessageId());
        }
        catch (Exception e)
        {

        }
    }

    public void removeAnswer(RemoveAnswerOperation removeAnswerOperation)
    {
        try{
            isConnected();
            courseController.removeAnswer(user, removeAnswerOperation.getCourseId(), removeAnswerOperation.getExamId(), removeAnswerOperation.getQuestionId(), removeAnswerOperation.getAnswerId());
        }
        catch (Exception e) {

        }
    }
    public void removeChatMessage(RemoveChatMessageOperation removeChatMessageOperation)
    {
        try{
            isConnected();
            courseController.removeChatMessage(user, removeChatMessageOperation.getCourseId(), removeChatMessageOperation.getChatId(), removeChatMessageOperation.getMessageId());
        }
        catch (Exception e) {

        }
    }
    public void removeChat(RemoveChatOperation removeChatOperation)
    {
        try{
            isConnected();
            courseController.removeChat(user, removeChatOperation.getCourseId(), removeChatOperation.getChatId());
        }
        catch (Exception e) {

        }
    }
    public void sendFriendRequest(SendFriendRequestOperation sendFriendRequestOperation)
    {
        try{
            isConnected();
            userController.sendFriendRequest(user.getEmail(),sendFriendRequestOperation.getEmail());
        }
        catch (Exception e)
        {

        }
    }
    public void addForbiddenWord(AddForbiddenWordOperation addForbiddenWordOperation)
    {
        try{
            isConnected();
            ForbiddenWords.getInstance().addForbiddenWord(addForbiddenWordOperation.getForbiddenWord());
        }
        catch (Exception e)
        {

        }
    }
    public void removeForbiddenWord(RemoveForbiddenWordOperation removeForbiddenWordOperation)
    {
        try{
            isConnected();
            ForbiddenWords.getInstance().removeForbiddenWord(removeForbiddenWordOperation.getForbiddenWord());
        }
        catch (Exception e)
        {

        }
    }

    public void resetPassword(ResetPasswordOperation resetPasswordOperation) {
        try {
            userController.resetPassword(resetPasswordOperation.getNewPassword(), resetPasswordOperation.getEmail());
            connections.send(connectionId, new Response((short) 1, (short) 31, "Password changed successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 31, e.getMessage()));
        }
    }

    public void forgotPassword(ForgotPasswordOperation forgotPasswordOperation) {
        try {
            userController.forgotPassword(forgotPasswordOperation.getEmail());
            connections.send(connectionId, new Response((short) 1, (short) 30, "Email sent successfully"));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 30, e.getMessage()));
        }
    }

    public void forgotPasswordCode(ForgotPasswordCodeOperation forgotPasswordCodeOperation) {
        try {
            userController.forgotPasswordCode(forgotPasswordCodeOperation.getForgotPasswordCode(), forgotPasswordCodeOperation.getEmail());
            connections.send(connectionId, new Response((short) 1, (short) 32, ""));
        }
        catch (Exception e) {
            connections.send(connectionId, new Response((short) 2, (short) 32, e.getMessage()));
        }
    }
}
