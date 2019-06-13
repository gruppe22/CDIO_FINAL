package rest;

import dao.*;
import dto.*;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Bruger {
    BrugerDAO dao = new BrugerDAO();

    @GET
    public List<BrugerDTO> getUserList() throws IBrugerDAO.DALException {
        List<BrugerDTO> list = dao.getBrugerList();
        return list;
    }

    @GET
    @Path("{id}")
    public BrugerDTO getUser(@PathParam("id") int id) throws IBrugerDAO.DALException {
        BrugerDTO user = dao.getBruger(id);
        return user;
    }

    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") int id) throws IBrugerDAO.DALException {

    }
}
