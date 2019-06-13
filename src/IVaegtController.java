//import gruppe22.cdio.business.IUserLogic;
import IVaegtLogik;
import dao.IRaavareBatchDAO;
import IVaegt;

public interface IVaegtController {

    public void setInterface(IVaegt vaegt);
    public void setVaegtLogik(IVaegtLogik vaegtLogik);

   // public void setUserLogic(IUserLogic userLogic);

    public void getOperatorID();
    public void getrbId();
    public void start() throws IRaavareBatchDAO.DALException;
    public String getRaavare(int rbId);
    public double getNetWeight(String netweightresult);
    public double getBruttoWeight(double net, String bruttoweightresult);



}
