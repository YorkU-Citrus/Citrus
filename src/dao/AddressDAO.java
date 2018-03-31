package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AddressBean;

public class AddressDAO {
	private static AddressDAO instance = null;
	
	public static synchronized AddressDAO getInstance()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (instance == null) {
			instance = new AddressDAO();
		}
		return instance;
	}
	
	private PreparedStatement getAddressByUserStatement;
	private PreparedStatement addAddressStatement;
	
	protected AddressDAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection connection = CitrusDAO.getInstance().getConnection();
		this.getAddressByUserStatement = connection.prepareStatement("SELECT * "
				+ "FROM citrus_shipping_address SA, "
				+ "( SELECT SA1.sauid, MAX(satime) as 'latestTime' FROM citrus_shipping_address SA1 WHERE sauid=? GROUP BY sauid ) tempTable "
				+ "WHERE SA.sauid=? AND SA.satime = tempTable.latestTime; ");
		this.addAddressStatement = connection.prepareStatement("INSERT INTO `citrus_shipping_address`(`said`, `sauid`, `satime`, `safirst`, `salast`, `sastreet`, `saprovince`, `sacountry`, `sazip`) "
				+ "VALUES (NULL,?,CURRENT_TIMESTAMP,?,?,?,?,?,?)");
	}
	
	//choose the lastest address
	public AddressBean getAddressByUser(int userId) throws SQLException{
		getAddressByUserStatement.setInt(1, userId);
		getAddressByUserStatement.setInt(2, userId);
		ResultSet result = getAddressByUserStatement.executeQuery();
		
		if(result.next()) {
			return new AddressBean(
					result.getInt("said"), 
					result.getInt("sauid"), 
					result.getString("safirst"), 
					result.getString("salast"), 
					result.getString("sastreet"), 
					result.getString("saprovince"), 
					result.getString("sacountry"), 
					result.getString("sazip"));
		}
		else {
			return null;
		}
	}
	
	//add or update
	public int addAddress(AddressBean address) throws SQLException {
		addAddressStatement.setInt(1, address.getUserId());
		addAddressStatement.setString(2, address.getFirstName());
		addAddressStatement.setString(3, address.getLastName());
		addAddressStatement.setString(4, address.getStreet());
		addAddressStatement.setString(5, address.getProvince());
		addAddressStatement.setString(6, address.getCountry());
		addAddressStatement.setString(7, address.getZip());
		
		return addAddressStatement.executeUpdate();
	}
	
	
	public static void main(String[] args) {
		try {
			AddressDAO test = AddressDAO.getInstance();
			AddressBean a1 = new AddressBean(0, 1, "u1_first", "u1_last", "younge", "ON", "Canada", "M3J 1P3");
			AddressBean a2 = new AddressBean(0, 1, "u1_first", "u1_last", "finch", "ON", "Canada", "M3K 4Q5");
			AddressBean a3 = new AddressBean(0, 2, "u2_first", "u2_last", "Byng", "ON", "Canada", "M4O UFO");
			
			
			//System.out.println(test.addAddress(a1));
			//System.out.println(test.addAddress(a2));
			//System.out.println(test.addAddress(a3));
			
			System.out.println(test.getAddressByUser(1));
			
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
