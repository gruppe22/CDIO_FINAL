package dao;

import dto.BrugerDTO;
import dto.RaavareBatchDTO;
import dto.RaavareDTO;

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
        public RaavareBatchDTO getRaavareBatch ( int rbId) throws Exception {
        RaavareBatchDTO rb = new RaavareBatchDTO();
        PreparedStatement ps = createConnection().prepareStatement("SELECT * FROM RaavareBatch WHERE rbId =?");
        ps.setInt(1,rbId);

        try {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                rb.setRbId(rs.getInt("rbId"));
                rb.setRaavareId(rs.getInt("raavareId"));
                rb.setMaengde(rs.getDouble("maengde"));
                rb.setLeverandoer(rs.getString("leverandoer"));
            } rs.close();
        } catch (SQLIntegrityConstraintViolationException ex){
            throw new IBrugerDAO.DALException("Fejl ved hentning af raavarebatch" +" "+ ex.getMessage());
        }catch (SQLException ex){
            throw new IRaavareBatchDAO.DALException(ex.getMessage());
        }
        return rb;
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
            try (Connection c = createConnection()){
                PreparedStatement ps = c.prepareStatement("insert into RaavareBatch values (?,?,?,?)");
                ps.setInt(1, raavarebatch.getRbId());
                ps.setInt(2, raavarebatch.getRaavareId());
                ps.setDouble(3, raavarebatch.getMaengde());
                ps.setString(4, raavarebatch.getLeverandoer());
                ps.execute();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IRaavareBatchDAO.DALException("Fejl ved oprettelse af raavarebatch:" +" "+ ex.getMessage());
            }catch (SQLException ex){
                throw new IRaavareBatchDAO.DALException(ex.getMessage());
            }
        }

        @Override
        public void updateRaavareBatch (RaavareBatchDTO raavarebatch) throws DALException {
            try (Connection c = createConnection()) {
                PreparedStatement ps = c.prepareStatement("UPDATE `RaavareBatch` SET `rbId`= ?,`RaavareId`= ?,`maengde` = ?, `leverandoer` =? WHERE `rbId` = ?;");
                ps.setInt(1, raavarebatch.getRbId());
                ps.setInt(2, raavarebatch.getRaavareId());
                ps.setDouble(3, raavarebatch.getMaengde());
                ps.setString(4, raavarebatch.getLeverandoer());
                ps.setInt(5,raavarebatch.getRbId());
                ps.executeUpdate();


            } catch (SQLException e) {
                throw new IRaavareBatchDAO.DALException(e.getMessage());
            }
        }
    }

