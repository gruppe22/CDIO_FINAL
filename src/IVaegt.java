import java.io.IOException;

public interface IVaegt {

    void closeConnection() throws IOException;
    void sendMessageBig(String msg);
    void sendMessageSmall(String msg);
    String tareWeight();
    String sendAndAwaitReturn(String msg);
    String readWeight();

}

