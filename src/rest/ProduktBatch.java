package rest;

import dto.ProduktBatchDTO;
import logic.ProduktBatchLogic;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("produktbatch")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduktBatch {

    ProduktBatchLogic produktLogic = new ProduktBatchLogic();

    @GET
    public Response getProduktBatchList() {
        try {
            List<ProduktBatchDTO> list = produktLogic.getProduktBatchList();
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
    public Response getProduktBatch(@PathParam("id") int id) {
        try {
            ProduktBatchDTO produkt = produktLogic.getProduktBatch(id);
            return Response.status(200)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(produkt)
                    .build();
        } catch (Exception ex) {
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    @POST
    public Response createProduktBatch(String body) {
        JSONObject json = new JSONObject(body);

        try {
            ProduktBatchDTO dto = new ProduktBatchDTO(
                    json.getInt("pbId"),
                    json.getInt("status"),
                    json.getInt("receptId")
            );
            produktLogic.createProduktBatch(dto);
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
