package rest;

import javax.json.*;
import javax.ws.rs.*;

@Path("User")
public class User {

	@GET
	@Path("/plant/")
	@Produces("application/json")
	public String getPrice(@DefaultValue("rose") @QueryParam("plantName") String name) {
		System.out.println("received:" + name);
		JsonObject result = Json.createObjectBuilder().add("firstName", "John").build();
		return result.toString();
	}

}
