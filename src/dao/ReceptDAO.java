package dao;

import dto.ReceptDTO;

import java.sql.*;
import java.util.List;

public class ReceptDAO implements IReceptDAO {

    private Connection createConnection() throws IReceptDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IReceptDAO.DALException(e.getMessage());
        }
    }

        @Override
        public ReceptDTO getRecept ( int receptId) throws DALException {
            return null;
        }

        @Override
        public List<ReceptDTO> getReceptList () throws DALException {
            return null;
        }

        @Override
        public void createRecept (ReceptDTO recept) throws DALException {
            try(Connection c = createConnection()) {
                PreparedStatement ps = c.prepareStatement("insert into Recept values (?,?,?)");
                ps.setInt(1, recept.getReceptId());
                ps.setString(2, recept.getReceptNavn());
                ps.setInt(3, recept.getRaavareId());
                ps.execute();
                c.close();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IReceptDAO.DALException("Fejl ved oprettelse af recept:" +" "+ ex.getMessage());
            }catch (SQLException ex){
                throw new IReceptDAO.DALException(ex.getMessage());
            }
        }

        @Override
        public void updateRecept (ReceptDTO recept) throws DALException {

        }
    }



