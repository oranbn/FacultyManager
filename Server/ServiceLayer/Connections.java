package ServiceLayer;

import java.io.IOException;

public interface Connections<T> {
    public int add(ConnectionHandler<T> connectionHandler);

    boolean send(int connectionId, T msg);

    void broadcast(T msg);

    void disconnect(int connectionId);
}