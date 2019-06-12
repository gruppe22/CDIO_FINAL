import java.io.IOException;
import java.time.LocalTime;

import dao.RaavareBatchDAO;
import dao.IRaavareBatchDAO;
import dao.RaavareDAO;
import dto.RaavareBatchDTO;
import dto.RaavareDTO;
import dto.BrugerDTO;

public class Vaegtlogik implements IVaegtLogik{

    private RaavareDAO raavareDAO = new RaavareDAO();
    private RaavareBatchDAO raavareBatchDAO = new RaavareBatchDTO();

    public Vaegtlogik() throws IOException, ClassNotFoundException {
    }

    @Override
    public RaavareDTO getRaavare(int batchId) {
        try {
            return raavareDAO.getRaavare(batchId);
        } catch (RaavareDTO.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveBatch(double tareweight, double netweight, double bruttoweight, int material, BrugerDTO bruger) throws IRaavareBatchDAO.DALException {
        RaavareBatchDTO dto = new RaavareBatchDTO();
        dto.setTareWeight(tareweight);
        dto.setNetWeight(netweight);
        dto.setGrossWeight(bruttoweight);
        dto.setRaavare(getRaavare(raavare));
        dto.setDateTime(LocalTime.now());
        dto.setOperator(user);
        batchDAO.createBatch(dto);
    }
}


