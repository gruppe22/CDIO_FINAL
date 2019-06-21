package rest;

import dto.*;
import logic.RaavareLogic;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("raavare")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Raavare {
    RaavareLogic raavareLogic = new RaavareLogic();

    @GET
    public Response getRaavareList() {
        try {
            List<RaavareDTO> list = raavareLogic.getRaavareList();
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
    public Response getRaavare(@PathParam("id") int id) {
        try {
            RaavareDTO raavare = raavareLogic.getRaavare(id);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(raavare)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    public Response createRaavare(String body) {
        JSONObject json = new JSONObject(body);

        try {
            RaavareDTO dto = new RaavareDTO(
                    json.getInt("raavareId"),
                    json.getString("raavareNavn"),
                    json.getString("leverandoer")
            );
            raavareLogic.createRaavare(dto);
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

    @PUT
    public Response updateRaavare(String body) {
        JSONObject json = new JSONObject(body);

        try {
            RaavareDTO dto = new RaavareDTO(
                    json.getInt("raavareId"),
                    json.getString("raavareNavn"),
                    json.getString("leverandoer")
            );
            raavareLogic.updateRaavare(dto);
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
