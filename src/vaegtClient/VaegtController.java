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
        raavareLogic = new RaavareLogic();
        receptLogic = new ReceptLogic();
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

    public void start() throws Exception {

        initializeWeight();

        /*
         * Get operator from weight
         */
        String input = socket.sendAndAwaitIntegerReturn("Indtast opr.id:", "", "");

        BrugerDTO user = null;
        String operatorNumber = null;
        operatorNumber = SubStringGenerator(input, "\"", "\"", 1);

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
            ProduktBatchKompDTO k = raavareAfvejning(r);
            produktBatchLogic.createProduktBatchKomp(k);
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

        productBatch = vaegtLogic.getProduktBatch(batchId);
        if (productBatch.getPbId() == 0){
            String i = socket.sendAndAwaitReturn("Batch findes ikke", "", "");
            receptApproval();
        }

        /*
         *Weight writes name of recipe and it is approved
         */
        recept = vaegtLogic.getRecept(productBatch.getReceptId());
        input = socket.sendAndAwaitIntegerReturn(recept.getReceptNavn() + " (1:Y,2:N)", "", "");

        if (!SubStringGenerator(input, "\"", "\"", 1).equals("1"))
            receptApproval();

        productBatch.setStatus(1);
        produktBatchLogic.updateProduktBatch(productBatch);
    }

    public ProduktBatchKompDTO raavareAfvejning(ReceptKompDTO receptKompDTO) throws Exception {
        double netWeight;
        RaavareBatchDTO batch = null;
        ProduktBatchKompDTO batchKompDTO = new ProduktBatchKompDTO();
        String input;

        input = socket.sendAndAwaitReturn("Er vaegt tom? (1:Y,2:N)", "", "");
        if (!SubStringGenerator(input, "\"", "\"", 1).equals("1"))
            raavareAfvejning(receptKompDTO);

        socket.tareWeight();

        socket.sendAndAwaitReturn("Stil beholder", "", "");
        double taraWeight = Double.parseDouble(SubStringGenerator(socket.readWeight(), "S", " ", 9));
        batchKompDTO.setTara(taraWeight);

        socket.tareWeight();

        input = socket.sendAndAwaitIntegerReturn(raavareLogic.getRaavare(receptKompDTO.getRaavareId()).getRaavareNavn() + " bId?", "", "");
        int rbId = Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));

        try {
            batch = raavareLogic.getRaavareBatch(rbId);
        } catch (Exception e) {
            input = socket.sendAndAwaitReturn("RavareBatch findes ikke", "", "");
            raavareAfvejning(receptKompDTO);
        }

        if (batch.getRaavareId() != receptKompDTO.getRaavareId()) {
            input = socket.sendAndAwaitReturn("Forkert RavareBatch", "", "");
            raavareAfvejning(receptKompDTO);
        }

        socket.sendAndAwaitReturn("Lav afvejning.", "", "");

        netWeight = getNetWeight(socket.readWeight());

        if (netWeight > receptKompDTO.getTolerance()) {
            input = socket.sendAndAwaitReturn("Kasser afvejning", "", "");
            batch.setMaengde(batch.getMaengde()-netWeight); // jeg håber jeg opdaterer mængden i databasen her så man kan føre lagerstatus
            raavareAfvejning(receptKompDTO);
        }

        batchKompDTO.setNetto(netWeight);
        batch.setMaengde(batch.getMaengde()-netWeight); // jeg håber jeg opdaterer mængden i databasen her så man kan føre lagerstatus

        return batchKompDTO;
    }
 }
