package rest;

import dto.*;
import logic.ReceptLogic;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("receptkomponent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceptKomponent {
    ReceptLogic receptLogic = new ReceptLogic();

    @GET
    @Path("{id}")
    public Response getReceptKomptList(@PathParam("id") int id) {
        try {
            List<ReceptKompDTO> list = receptLogic.getReceptKompList(id);
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
