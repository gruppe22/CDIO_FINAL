package vaegtClient;

import dto.*;
import logic.*;
import rest.RaavareBatch;
import rest.Recept;

import java.util.List;

public class VaegtController {
    private VaegtSocket socket;
    private VaegtLogic vaegtLogic;
    private BrugerLogic userLogic;
    private ProduktBatchLogic produktBatchLogic;
    private RaavareLogic raavareLogic;
    private ReceptLogic receptLogic;

    ProduktBatchDTO productBatch;
    ReceptDTO recept;

    public VaegtController() {
        vaegtLogic = new VaegtLogic();
        userLogic = new BrugerLogic();
        produktBatchLogic = new ProduktBatchLogic();
    }

    public void setSocket(VaegtSocket socket) {
        this.socket = socket;
    }

    public double getNetWeight(String netWeightResult) {
        double netWeight = Double.parseDouble(SubStringGenerator(netWeightResult, "S", " ", 9));
        return netWeight;
    }

  /*  public double getBruttoWeight(double net, String bruttoWeightResult) {
        double bruttoWeight = net + Double.parseDouble(SubStringGenerator(bruttoWeightResult, "S", " ", 9));
        return bruttoWeight;
    }*/

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

    public void setRaavareLogic(RaavareLogic raavareLogic) {
        this.raavareLogic = raavareLogic;
    }

    public void start() throws Exception {

        initializeWeight();

        /*
         * Get operator from weight
         */
        String input = socket.sendAndAwaitIntegerReturn("Indtast opr.id:", "", "");

        BrugerDTO user = null;
        String operatorNumber = null;
        operatorNumber = SubStringGenerator(input, "\"", "\"", 1);

        System.out.println(operatorNumber);
        try {
            user = userLogic.getBruger(Integer.parseInt(operatorNumber));
        } catch (Exception e) {
            String i = socket.sendAndAwaitReturn("Bruger findes ikke", "", "");
            start();
        }

        /*
         * Approve operator
         */
        input = socket.sendAndAwaitIntegerReturn(user.getOprNavn() + "?" + "(1:Y, 2:N)", "", "");
        if (!SubStringGenerator(input, "\"", "\"", 1).equals("1"))
            start();

        receptApproval();

        List<ReceptKompDTO> receptKomponenter = receptLogic.getReceptKompList(recept.getReceptId());

        for (ReceptKompDTO r : receptKomponenter) {
            raavareAfvejning(r);
        }

        productBatch.setStatus(2);
        produktBatchLogic.updateProduktBatch(productBatch);
    }

    public void receptApproval() throws Exception {
        /*
         * Get product-batch number from weight
         */
        String input = socket.sendAndAwaitIntegerReturn("Produktbatch id:", "", "");
        int batchId = Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));

        try {
            productBatch = vaegtLogic.getProduktBatch(batchId);
        } catch (Exception e) {
            String i = socket.sendAndAwaitReturn("Batch findes ikke", "", "");
            receptApproval();
        }

        /*
         *Weight writes name of recipe and it is approved
         */
        recept = vaegtLogic.getRecept(productBatch.getReceptId());
        input = socket.sendAndAwaitIntegerReturn(recept.getReceptNavn() + "Recept? (1:Y,2:N)", "", "");

        if (!SubStringGenerator(input, "\"", "\"", 1).equals("1"))
            receptApproval();

        productBatch.setStatus(1);
        produktBatchLogic.updateProduktBatch(productBatch);
    }

    public void raavareAfvejning(ReceptKompDTO receptKompDTO) throws Exception {
        double netWeight;
        RaavareBatchDTO batch = null;

        String i = socket.sendAndAwaitReturn("Er vaegt tom? (1:Y,2:N)", "", "");
        if (!SubStringGenerator(i, "\"", "\"", 1).equals("1"))
            raavareAfvejning(receptKompDTO);

        socket.tareWeight();

        String input = socket.sendAndAwaitIntegerReturn("RaavareBatchId:", "", "");
        int rbId = Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));

        try {
            batch = raavareLogic.getRaavareBatch(rbId);
        } catch (Exception e) {
            String i = socket.sendAndAwaitReturn("RavareBatch findes ikke", "", "");
            raavareAfvejning(receptKompDTO);
        }

        if (batch.getRaavareId() != receptKompDTO.getRaavareId()) {
            String i = socket.sendAndAwaitReturn("Forkert RavareBatch", "", "");
            raavareAfvejning(receptKompDTO);
        }

        socket.sendAndAwaitReturn("Stil beholder", "", "");
        double taraweight = Double.parseDouble(SubStringGenerator(socket.readWeight(), "S", " ", 9));

        //TODO: denne vægt skal gemmes - produktKompBatchDTO

        socket.tareWeight();

        socket.sendAndAwaitReturn("Lav afvejning.", "", "");
        netWeight = getNetWeight(socket.readWeight());

        //TODO: denne vægt skal gemmes - produktKompBatchDTO

        //TODO lav kode til at vurdere om det er indenfor tolerancen
        Double tolerance = null; // her skal hentes nomNetto + tolerance fra ReceptKomp
        if (netWeight > tolerance) {
            input = socket.sendAndAwaitReturn("Kasser afvejning", "", "");

        }
        // TODO: opdater lagerstatus?
    }
 }
