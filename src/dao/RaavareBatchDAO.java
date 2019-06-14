package dao;

import dto.RaavareBatchDTO;

import java.sql.*;
import java.util.List;

public class RaavareBatchDAO implements IRaavareBatchDAO {
    private Connection createConnection() throws IRaavareBatchDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IRaavareBatchDAO.DALException(e.getMessage());
        }
    }

    public RaavareBatchDAO() {
    }

    @Override
        public RaavareBatchDTO getRaavareBatch ( int rbId) throws DALException {
            return null;
        }

        @Override
        public List<RaavareBatchDTO> getRaavareBatchList () throws DALException {
            return null;
        }

        @Override
        public List<RaavareBatchDTO> getRaavareBatchList ( int raavareId) throws DALException {
            return null;
        }

        @Override
        public void createRaavareBatch (RaavareBatchDTO raavarebatch) throws DALException {
            Connection c = createConnection();
            try {
                PreparedStatement ps = c.prepareStatement("insert into RaavareBatch values (?,?,?,?)");
                ps.setInt(1, raavarebatch.getRbId());
                ps.setInt(2, raavarebatch.getRaavareId());
                ps.setDouble(3, raavarebatch.getMaengde());
                ps.setString(4, raavarebatch.getLeverandoer());
                ps.execute();
                c.close();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IRaavareBatchDAO.DALException("Fejl ved oprettelse af raavarebatch");
            }catch (SQLException ex){
                throw new IRaavareBatchDAO.DALException(ex.getMessage());
            }
        }

        @Override
        public void updateRaavareBatch (RaavareBatchDTO raavarebatch) throws DALException {

        }
    }

