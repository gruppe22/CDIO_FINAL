package rest;

import dto.*;
import logic.ReceptLogic;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("recept")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Recept {
    ReceptLogic receptLogic = new ReceptLogic();

    @GET
    @Path("{id}")
    public Response getRecept(@PathParam("id") int id) {
        try {
            ReceptDTO recept = receptLogic.getRecept(id);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(recept)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @GET
    public Response getReceptList() {
        try {
            List<ReceptDTO> list = receptLogic.getReceptList();
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

    @POST
    public Response createRecept(String body) {
        JSONObject json = new JSONObject(body);

        try {
            ReceptDTO dto = new ReceptDTO(
                    json.getInt("receptId"),
                    json.getString("receptNavn")
            );
            receptLogic.createRecept(dto);
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

    @POST
    @Path("komponent")
    public Response createReceptKomp(String body) {
        JSONObject json = new JSONObject(body);

        try {
            ReceptKompDTO dto = new ReceptKompDTO(
                    json.getInt("receptId"),
                    json.getInt("raavareId"),
                    json.getDouble("nomNetto"),
                    json.getDouble("tolerance")
            );
            receptLogic.createReceptKomp(dto);
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
