package vaegtClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VaegtSocket {
    private java.net.Socket socket;
    private PrintWriter output;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private OutputStream outputStream;

    public VaegtSocket() {
        try {
            String host = "169.254.2.2";
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

    public void closeConnection() throws IOException {
        socket.close();
    }

    public void sendMessageBig(String msg) {
        output.println("D \""+msg +"\" crlf");
    }

    public void sendMessageSmall(String msg) {
        output.println("P111 \""+msg +"\" crlf");
    }

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

    public String sendAndAwaitReturn(String msg, String msg2, String msg3) throws Exception {
        output.println("RM20 3 \""
                +msg+ "\" " + "\""
                +msg2+ "\" " + "\""
                +msg3 + "\" ");

        String input2 =null ;
        String input1 = bufferedReader.readLine();
        System.out.println("1st response from weight : "+input1);
        while(input1 != null){
            if(input1.contains("RM20 B")){
                input2 = bufferedReader.readLine();
                break;
            }
            else input1 = bufferedReader.readLine();
        }

        return input2;
    }

    public String sendAndAwaitIntegerReturn(String msg, String msg2, String msg3) throws Exception {
        output.println("RM20 3 \""
                +msg+ "\" " + "\""
                +msg2+ "\" " + "\""
                +msg3 + "\" ");

        String input2 =null ;
        String input1 = bufferedReader.readLine();
        System.out.println("1st response from weight : "+input1);
        while(input1 != null){
            if(input1.contains("RM20 B")){
                input2 = bufferedReader.readLine();
                break;
            }
            else input1 = bufferedReader.readLine();
        }





        return input2;
    }

    public String readWeight() {
        output.println("S");
        String returnvalue = null;
        try {
            returnvalue = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnvalue;
    }
}
