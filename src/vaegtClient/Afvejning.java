package vaegtClient;

public class Afvejning {

    public static void main(String[] args) throws Exception {
        VaegtSocket socket = new VaegtSocket();
        VaegtController controller = new VaegtController();

        controller.setSocket(socket);
        controller.initializeWeight();
        controller.start();
    }
}
