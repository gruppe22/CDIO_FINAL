package vaegtClient;

import com.sun.xml.internal.bind.v2.TODO;
import dto.BrugerDTO;
import dto.ProduktBatchDTO;
import dto.ReceptDTO;
import logic.BrugerLogic;
import logic.VaegtLogic;

import java.util.List;

public class VaegtController {
    private VaegtSocket socket;
    private VaegtLogic vaegtLogic;
    private BrugerLogic userLogic;

    public VaegtController() {
        vaegtLogic = new VaegtLogic();
        userLogic = new BrugerLogic();

    }

    public void setSocket(VaegtSocket socket) {
        this.socket = socket;
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
    public void initializeWeight(){
        String input = socket.readWeight();
        while(input != null){
            if(input.contains("kg")){
                break;
            }
            else if(input.contains("ES")){
                input=socket.readWeight();
            }
            else input = socket.readWeight();
        }

    }
    public void start() throws Exception {
        //String material;
        double netWeight;
        double bruttoWeight;

        /*
         * Get operator from weight
         */
        String input = socket.sendAndAwaitIntegerReturn("Indtast op. nummer", " ", " ");

            BrugerDTO user = null;
            String operatorNumber = null;
            operatorNumber = SubStringGenerator(input, "\"", "\"", 1);


        System.out.println(operatorNumber);
        try {
            user = userLogic.getBruger(Integer.parseInt(operatorNumber));
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * Approve operator
         */
        System.out.println(user.getOprNavn());
        input = socket.sendAndAwaitIntegerReturn(user.getOprNavn()+"?" + "(1:Y, 2:N)","" ,"");
        if (SubStringGenerator(input, "\"","\"", 1).equals("1")) {

            /*
             * Get product-batch number from weight
             */
            input = socket.sendAndAwaitIntegerReturn("Indtast pb: ","","");
            int batchId =  Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));
            ProduktBatchDTO productBatch = vaegtLogic.getProduktBatch(batchId);
            System.out.println(batchId);
            System.out.println(productBatch.toString());

            /*
            *Weight writes name of recipe and it is approved
             */
            ReceptDTO recept = vaegtLogic.getRecept(productBatch.getReceptId());
            input = socket.sendAndAwaitIntegerReturn(recept.getReceptNavn() +"? (1:Y,2:N)","", "");
            if (SubStringGenerator(input, "\"","\"", 1).equals("1")){

            /*
             * Vejning startes
             */
            input = socket.sendAndAwaitReturn("Er vaegt tom? (1:Y,2:N)","" ,"");
                if (SubStringGenerator(input, "\"","\"", 1).equals("1")){

        //TODO: Lav pkt 8 : produktbatch nummeret sættes til "under produktion"

                input = socket.tareWeight();

                //TODO: denne vægt skal gemmes - produktKompBatchDTO

                input = socket.sendAndAwaitReturn("Stil beholder" , "", "");
                double taraweight = Double.parseDouble(SubStringGenerator(socket.readWeight(), "S", " ", 9));

                //TODO: denne vægt skal gemmes hvor?

                input = socket.tareWeight();

                socket.sendAndAwaitReturn("Placer netto på vaegten", " ", " ");
            netWeight = getNetWeight(socket.readWeight());
            socket.tareWeight();
            socket.sendAndAwaitReturn("Fjern venligst brutto fra vaegten"," ", "");
            bruttoWeight = getBruttoWeight(netWeight, socket.readWeight());
            input = socket.sendAndAwaitReturn("OK (1) eller Kasseret (2) ?", " " , " ");

            if (SubStringGenerator(input, "\"", "\"", 1).equals("1")) {
                // save
            }
            else {
                start();
            }
        }
        else {
            start();
        }
        } else start();
    }
    }}