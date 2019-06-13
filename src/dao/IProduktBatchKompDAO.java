package dao;

import dto.ProduktBatchKompDTO;

import java.util.List;

public interface IProduktBatchKompDAO {
    ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
    List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException;
    List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
    void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
    void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
    class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }
}