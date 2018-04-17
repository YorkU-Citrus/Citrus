package core;

import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import bean.BookBean;
import dao.BookDAO;

@Path("book")
public class Book {

	@POST
	@Path("/")
	@Produces("application/json")
	public String RestAddBook(@FormParam("title") String title, @FormParam("price") int price,
			@FormParam("category") int categoryId, @FormParam("isbn") String isbn,
			@FormParam("description") String description, @FormParam("amount") int amount,
			@FormParam("image") String image, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*"); // TODO: Please remove after testing
		try {
			BookBean data = new BookBean(title, price, categoryId, isbn, description, amount, image);
			BookDAO dataSource = BookDAO.getInstance();
			int id = dataSource.addBook(data);
			return Json.createObjectBuilder().add("success", id).build().toString();
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return Json.createObjectBuilder().add("error", e.getMessage()).build().toString();
		}

	}

	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public String RestGetBookById(@PathParam("id") int bookId, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*"); // TODO: Please remove after testing
		
		try {
			BookDAO dataSource = BookDAO.getInstance();
			BookBean data = dataSource.getBookByID(bookId);
			return Json.createObjectBuilder().add("id", data.getBookId()).add("title", data.getTitle())
					.add("categoryId", data.getCategory()).add("category", data.getCategoryTitle())
					.add("description", data.getDescription()).add("image", data.getImage())
					.add("amount", data.getAmount()).add("rating", data.getRating())
					.add("numberOfComment", data.getNumberOfComment()).add("isbn", data.getIsbn())
					.add("price", data.getPrice()).build().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Json.createObjectBuilder().build().toString();
		}
	}

	@GET
	@Path("/category/{id}/{offset}")
	@Produces("application/json")
	public String RestGetBookByCategory(@PathParam("id") int categoryId, @PathParam("offset") int offset,
			@Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*"); // TODO: Please remove after testing
		try {
			BookDAO dataSource = BookDAO.getInstance();
			List<BookBean> data = dataSource.getBooksByCategory(categoryId, offset, 10);
			JsonArrayBuilder list = Json.createArrayBuilder();
			for (BookBean b : data) {
				list.add(Json.createObjectBuilder().add("id", b.getBookId()).add("title", b.getTitle())
						.add("categoryId", b.getCategory()).add("category", b.getCategoryTitle())
						.add("description", b.getDescription()).add("image", b.getImage()).add("amount", b.getAmount())
						.add("rating", b.getRating()).add("numberOfComment", b.getNumberOfComment())
						.add("isbn", b.getIsbn()).add("price", b.getPrice()));
			}
			return Json.createObjectBuilder().add("count", data.size()).add("list", list).build().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Json.createObjectBuilder().build().toString();
		}
	}

	@GET
	@Path("/all/{offset}")
	@Produces("application/json")
	public String RestGetBook(@PathParam("offset") int offset, @Context HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*"); // TODO: Please remove after testing
		try {
			BookDAO dataSource = BookDAO.getInstance();
			List<BookBean> data = dataSource.getBook(offset, 10);
			JsonArrayBuilder list = Json.createArrayBuilder();
			for (BookBean b : data) {
				list.add(Json.createObjectBuilder().add("id", b.getBookId()).add("title", b.getTitle())
						.add("categoryId", b.getCategory()).add("category", b.getCategoryTitle())
						.add("description", b.getDescription()).add("image", b.getImage()).add("amount", b.getAmount())
						.add("rating", b.getRating()).add("numberOfComment", b.getNumberOfComment())
						.add("isbn", b.getIsbn()).add("price", b.getPrice()));
			}
			return Json.createObjectBuilder().add("count", data.size()).add("list", list).build().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Json.createObjectBuilder().build().toString();
		}
	}

	public static void main(String[] args) {

	}

}
