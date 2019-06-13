package dao;

import dto.ProduktBatchDTO;

import java.util.List;

public interface IProduktBatchDAO {
    ProduktBatchDTO getProduktBatch(int pbId) throws Exception;
    List<ProduktBatchDTO> getProduktBatchList() throws Exception;
    void createProduktBatch(ProduktBatchDTO produktbatch) throws Exception;
    void updateProduktBatch(ProduktBatchDTO produktbatch) throws Exception;
}