package rest;

import dao.*;
import dto.*;
import logic.BrugerLogic;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class Users {
    BrugerLogic userLogic;

    public Users() throws Exception {
        try {
            userLogic = new BrugerLogic();
        } catch (Exception ex) {
            throw new Exception("Server error");
        }
    }

    @GET
    public List<BrugerDTO> getUserList() throws Exception {
        try {
            List<BrugerDTO> list = userLogic.getBrugerList();
            return list;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @GET
    @Path("{id}")
    public BrugerDTO getUser(@PathParam("id") int id) throws Exception {
        try {
            BrugerDTO user = userLogic.getBruger(id);
            return user;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
