// import gruppe22.cdio.business.IUserLogic;

import IVaegtLogik;
import dao.IRaavareBatchDAO;
import dao.IBrugerDAO;
import dao.BrugerDAO;
import dto.RaavareDTO;
import dto.BrugerDTO;

//import gruppe22.cdio.io.IUserInterface;

import IVaegt;

import java.io.IOException;
import java.util.StringTokenizer;

public class VaegtController implements IVaegtController{

        private IVaegt vaegt;
        private IVaegtLogik vaegtLogik;

       // private IUserLogic userLogic;

        @Override
        public void setInterface(IVaegt vaegt{
            this.vaegt = vaegt;
        }

        @Override
        public void setVaegtLogik(IVaegtLogik logik) {
            this.vaegtLogik = logik;
        }

      /*  @Override
        public void setUserLogic(IUserLogic userLogic) {
            this.userLogic = userLogic;
        }*/

        @Override
        public void getOperatorID() {

        }

        @Override
        public void getrbId() {

        }

        @Override
        public void start() throws IRaavareBatchDAO.DALException {
            String raavare;
            double netweight;
            double bruttoweight;
            String input = vaegt.sendAndAwaitReturn("Indtast op. nummer");
            String operatorNumber = SubStringGenerator(input, "\"", "\"", 1);
            BrugerDTO bruger = null;

            try {
                user = userLogic.getUser(Integer.parseInt(operatorNumber));
            } catch (IUserDAO.DALException e) {
                e.printStackTrace();
            }

            input = vaegt.sendAndAwaitReturn(user.getUserName() + " - Er dette korrekt (1:Y, 2:N)");
            if (SubStringGenerator(input, "\"", "\"", 1).equals("1")) {
                input = vaegt.sendAndAwaitReturn("Indtast materialenummer: ");
                int batchid =  Integer.parseInt(SubStringGenerator(input, "\"", "\"", 1));
                material = getMaterial(batchid);
                vaegt.sendAndAwaitReturn("Vaegten skal vaere ubelastet.");
                vaegt.tareWeight();
                vaegt.sendAndAwaitReturn("Placer venligst tara på vaegten.");
                double taraweight = Double.parseDouble(SubStringGenerator(vaegt.readWeight(), "S", " ", 9));
                vaegt.tareWeight();
                vaegt.sendAndAwaitReturn("Placer netto på vaegten");
                netweight = getNetWeight(vaegt.readWeight());
                vaegt.tareWeight();
                vaegt.sendAndAwaitReturn("Fjern venligst brutto fra vaegten");
                bruttoweight = getBruttoWeight(netweight, vaegt.readWeight());
                input = vaegt.sendAndAwaitReturn("OK (1) eller Kasseret (2) ?");
                if (SubStringGenerator(input, "\"", "\"", 1).equals("1")) {
                    saveBatch(taraweight, netweight, bruttoweight, batchid, bruger);
                }
                else {
                    start();
                }
            }
            else {
                start();
            }

        }

        @Override
        public String getMaterial(int batchnumber) {
            return vaegtLogik.getRaavare(batchnumber).getMaterial();
        }

        @Override
        public double getNetWeight(String netweightresult) {
            double netweight = Double.parseDouble(SubStringGenerator(netweightresult, "S", " ", 9));
            return netweight;
        }

        @Override
        public double getBruttoWeight(double net, String bruttoweightresult) {
            double bruttoweight = net + Double.parseDouble(SubStringGenerator(bruttoweightresult, "S", " ", 9));
            return bruttoweight;


        }

        private String SubStringGenerator(String source, String s, String e, int offset) {
            int opening = source.indexOf(s) + offset;
            int closing = source.indexOf(e, opening + 1);

            String returnString = source.substring(opening, closing);
            return returnString;
        }

        public void saveBatch(double tareweight, double netweight, double bruttoweight, int batchid, BrugerDTO bruger) throws IBatchDAO.DALException {
            vaegtLogik.saveBatch(tareweight, netweight, bruttoweight, batchid, bruger);
        }
    }