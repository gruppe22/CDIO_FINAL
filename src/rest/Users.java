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
public class Users {
    BrugerDAO dao = new BrugerDAO();

    @GET
    public List<IUserDTO> getUserList() throws IBrugerDAO.DALException {
        List<IUserDTO> list = dao.getUserList();
        return list;
    }

    @GET
    @Path("{id}")
    public IUserDTO getUser(@PathParam("id") int id) throws IBrugerDAO.DALException {
        IUserDTO user = dao.getUser(id);
        return user;
    }

    @POST
    public Response createUser(String body) throws IBrugerDAO.DALException {
        JSONObject json = new JSONObject(body);

        if (json.isNull("role") || json.get("role").equals("")
                || json.isNull("userId") || json.get("userId").equals("")
                || json.isNull("cprNumber") || json.get("cprNumber").equals("")
                || json.isNull("ini") || json.get("ini").equals("")
                || json.isNull("userName") || json.get("userName").equals(""))
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Udfyld alle felter")
                    .build();

        IUserDTO u = dao.getUser(json.getInt("userId"));

        if (u.getUserId() == json.getInt("userId"))
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Bruger-ID findes allerede - prøv et andet")
                    .build();

        UserDTO user = new UserDTO(json.getInt("userId"), json.getString("userName"), json.getString("ini"), json.getInt("cprNumber"));
        user.setRole(json.getString("role"));
        user.setPassword();

        dao.createUser(user);
        return Response.status(200)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(user)
                .build();
    }

    @PUT
    public Response updateUser(String body) throws IBrugerDAO.DALException {
        JSONObject json = new JSONObject(body);

        if (json.isNull("role") || json.get("role").equals("")
                || json.isNull("userId") || json.get("userId").equals("")
                || json.isNull("cprNumber") || json.get("cprNumber").equals("")
                || json.isNull("ini") || json.get("ini").equals("")
                || json.isNull("userName") || json.get("userName").equals(""))
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Udfyld alle felter")
                    .build();

        IUserDTO u = dao.getUser(json.getInt("userId"));

        if (u.getUserId() != json.getInt("userId"))
            return Response.status(400)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Brugeren findes ikke - opret brugeren først")
                    .build();

        UserDTO user = new UserDTO(json.getInt("userId"), json.getString("userName"), json.getString("ini"), json.getInt("cprNumber"));
        user.setRole(json.getString("role"));

        dao.updateUser(user);
        return Response.status(200)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(user)
                .build();
    }

    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") int id) throws IBrugerDAO.DALException {

    }
}
