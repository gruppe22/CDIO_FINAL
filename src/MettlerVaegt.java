import java.io.*;
import java.net.Socket;

public class MettlerVaegt implements IVaegt{

    private Socket socket;
    private PrintWriter output;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private OutputStream outputStream;


    public  MettlerVaegt() {
        try {
            String host = "127.0.0.1";
            int port = 8000;
            socket = new Socket(host, port);

            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();


            output = new PrintWriter(outputStream, true);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void closeConnection() throws IOException {
        socket.close();
    }

    @Override
    public void sendMessageBig(String msg) {
        output.println("D \""+msg +"\" crlf");
    }

    @Override
    public void sendMessageSmall(String msg) {
        output.println("P111 \""+msg +"\" crlf");
    }

    @Override
    public String tareWeight() {
        output.println("T crlf");
        String returnvalue = null;
        try {
            returnvalue = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }



    @Override
    public String sendAndAwaitReturn(String msg) {
        output.println("RM20 8 \"" +msg+ "\" \"\" \"&3\" crlf");

        String returnvalue = null;
        try {
            bufferedReader.readLine();
            returnvalue = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }

    @Override
    public String readWeight() {
        output.println("S crlf");
        String returnvalue = null;
        try {
            returnvalue = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }
}



