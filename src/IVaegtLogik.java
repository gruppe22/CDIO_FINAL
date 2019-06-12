import dao.IRaavareBatchDAO;
import dto.RaavareDTO;
import dto.BrugerDTO;

public interface IVaegtLogik {
        RaavareDTO getRaavare(int rbId);

        void saveBatch(double tareweight, double netweight, double bruttoweight, int raavare, BrugerDTO bruger) throws IRaavareBatchDAO.DALException;
}
