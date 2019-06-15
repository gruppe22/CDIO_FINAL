package rest;

import dto.*;
import logic.BrugerLogic;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("brugere")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Brugere {

    BrugerLogic userLogic = new BrugerLogic();

    @GET
    public Response getUserList() {
        try {
            List<BrugerDTO> list = userLogic.getBrugerList();
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(list)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        try {
            BrugerDTO user = userLogic.getBruger(id);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(user)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    public Response createUser(String body) {
        JSONObject json = new JSONObject(body);

        try {
            BrugerDTO user = new BrugerDTO(
                    json.getInt("oprId"),
                    json.getString("oprNavn"),
                    json.getString("ini"),
                    json.getString("cpr"),
                    json.getString("rolle")
            );
            userLogic.createBruger(user);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(user)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @PUT
    public Response updateUser(String body) {
        JSONObject json = new JSONObject(body);

        try {
            BrugerDTO dto = new BrugerDTO(
                    json.getInt("oprId"),
                    json.getString("oprNavn"),
                    json.getString("ini"),
                    json.getString("cpr"),
                    json.getString("rolle")
            );
            userLogic.updateBruger(dto);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(dto)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
