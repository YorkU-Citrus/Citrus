package service;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Path("XML")
public class OrderProcess {

    @Context ServletContext servletContext;
    
	@GET
	@Path("/")
	@Produces("text/xml")
	public String getOrderByPartNumber(@QueryParam("partNumber") int partNumber) {
        String actualPath = servletContext.getRealPath("/");
        System.out.println(String.format("Hello, world! \nActual path: %s", actualPath));
		return "test";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
