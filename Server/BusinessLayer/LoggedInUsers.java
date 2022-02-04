package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class LoggedInUsers {
    private final List<String> loggedInUsersList;
    public LoggedInUsers(){
        loggedInUsersList = new ArrayList<>();
    }
    public void add(String email)
    {
        loggedInUsersList.add(email);
    }
    public boolean remove(String email)
    {
        return loggedInUsersList.remove(email);
    }
    public boolean isLoggedIn(String email)
    {
        return loggedInUsersList.contains(email);
    }
}
