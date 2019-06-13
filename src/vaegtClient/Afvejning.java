package vaegtClient;

public class Afvejning {

    public static void main(String[] args) {
        VaegtSocket socket = new VaegtSocket();
        VaegtController controller = new VaegtController();

        controller.setSocket(socket);
        controller.start();
    }
}
