package service;

import java.io.File;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

@Path("XML")
public class OrderProcess {

	@Context
	ServletContext servletContext;

	@GET
	@Path("/")
	@Produces("text/xml")
	public String getOrderByPartNumber(@DefaultValue("0") @QueryParam("offset") long offset,
			@DefaultValue("65536") @QueryParam("limit") long limit)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JAXBException,
			SAXException {
		String actualPath = servletContext.getRealPath("/");
		String xsdPath = actualPath + "order-list.xsd";
		// System.out.println(String.format("Hello, world! \nActual path: %s",
		// actualPath));
		OrderWrapper data = new OrderWrapper(offset, limit);

		// Create a context
		JAXBContext jaxbContext = JAXBContext.newInstance(OrderWrapper.class); // instantiate a context

		// Create a marshaler
		Marshaller marshaller = jaxbContext.createMarshaller(); // create a marshaler
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Instantiate the XML Schema
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(xsdPath));
		marshaller.setSchema(schema);
		StringWriter sw = new StringWriter(); // standard IO
		marshaller.marshal(data, new StreamResult(sw));
		//System.out.println(sw.toString());

		return sw.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
