package dao;

import dto.ProduktBatchDTO;

import java.util.List;

public interface IProduktBatchDAO {
    ProduktBatchDTO getProduktBatch(int pbId) throws Exception;
    List<ProduktBatchDTO> getProduktBatchList() throws Exception;
    void createProduktBatch(ProduktBatchDTO produktbatch) throws Exception;
    void updateProduktBatch(ProduktBatchDTO produktbatch) throws Exception;

    class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        DALException(String msg) {
            super(msg);
        }

    }
}