package vaegtClient;

import dto.BrugerDTO;
import logic.BrugerLogic;
import logic.VaegtLogic;

public class VaegtController {
    private VaegtSocket socket;
    private VaegtLogic vaegtLogic;
    private BrugerLogic userLogic;

    public void setSocket(VaegtSocket socket) {
        this.socket = socket;
    }

    public void setBrugerLogic(BrugerLogic userLogic) {
        this.userLogic = userLogic;
    }

    public void start() {
        String material;
        double netWeight;
        double bruttoWeight;
        String input = socket.sendAndAwaitReturn("Indtast op. nummer");
        String operatorNumber = SubStringGenerator(input, "\"", "\"", 1);
        BrugerDTO user = null;

        try {
            user = userLogic.getBruger(Integer.parseInt(operatorNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }

        input = socket.sendAndAwaitReturn(user.getOprNavn() + " - Er dette korrekt (1:Y, 2:N)");

        if (SubStringGenerator(input, "\"", "\"", 1).equals("1")) {
            input = socket.sendAndAwaitReturn("Indtast materialenummer: ");
            int batchid =  Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));
            material = getMaterial(batchid);
            socket.sendAndAwaitReturn("Vaegten skal vaere ubelastet.");
            socket.tareWeight();
            socket.sendAndAwaitReturn("Placer venligst tara på vaegten.");
            double taraweight = Double.parseDouble(SubStringGenerator(socket.readWeight(), "S", " ", 9));
            socket.tareWeight();
            socket.sendAndAwaitReturn("Placer netto på vaegten");
            netWeight = getNetWeight(socket.readWeight());
            socket.tareWeight();
            socket.sendAndAwaitReturn("Fjern venligst brutto fra vaegten");
            bruttoWeight = getBruttoWeight(netWeight, socket.readWeight());
            input = socket.sendAndAwaitReturn("OK (1) eller Kasseret (2) ?");

            if (SubStringGenerator(input, "\"", "\"", 1).equals("1")) {
                saveBatch(taraweight, netWeight, bruttoWeight, batchid, user);
            }
            else {
                start();
            }
        }
        else {
            start();
        }
    }

    public String getMaterial(int batchNumber) {
        return "";
        //return vaegtLogic.getMaterial(batchNumber).getMaterial();
    }

    public double getNetWeight(String netWeightResult) {
        double netWeight = Double.parseDouble(SubStringGenerator(netWeightResult, "S", " ", 9));
        return netWeight;
    }

    public double getBruttoWeight(double net, String bruttoWeightResult) {
        double bruttoWeight = net + Double.parseDouble(SubStringGenerator(bruttoWeightResult, "S", " ", 9));
        return bruttoWeight;
    }

    private String SubStringGenerator(String source, String s, String e, int offset) {
        int opening = source.indexOf(s) + offset;
        int closing = source.indexOf(e, opening + 1);

        String returnString = source.substring(opening, closing);
        return returnString;
    }

    public void saveBatch(double tareWeight, double netWeight, double bruttoWeight, int batchId, BrugerDTO user) {
        //vaegtLogic.saveBatch(tareWeight, netWeight, bruttoWeight, batchId, user);
    }
}
