package rest;

import dao.*;
import dto.*;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("vaegt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Vaegt {

    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") int id) throws IBrugerDAO.DALException {

    }
}
