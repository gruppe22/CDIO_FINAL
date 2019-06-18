package rest;

import dto.*;
import logic.RaavareLogic;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("raavarebatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaavareBatch {
    RaavareLogic raavareLogic = new RaavareLogic();

    @GET
    public Response getRaavareBatchList() {
        try {
            List<RaavareBatchDTO> list = raavareLogic.getRaavareBatchList();
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
    public Response getRaavareBatchList(@PathParam("id") int id) {
        try {
            List<RaavareBatchDTO> list = raavareLogic.getRaavareBatchList(id);
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
    public Response createRaavareBatch(String body) {
        JSONObject json = new JSONObject(body);

        try {
            RaavareBatchDTO dto = new RaavareBatchDTO(
                    json.getInt("rbId"),
                    json.getInt("raavareId"),
                    json.getDouble("maengde")
            );
            raavareLogic.createRaavareBatch(dto);
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
