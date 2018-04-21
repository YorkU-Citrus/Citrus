package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bean.OrderBean;
import dao.OrderDAO;


@XmlRootElement(name="orderList")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderWrapper {

	@XmlElement	(name="order")
	public List<OrderBean> list;
	
	public OrderWrapper() {
		
	}
	
	public OrderWrapper(long offset, long limit) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.list = OrderDAO.getInstance().getAllOrders(offset, limit);
		//System.out.println(list);
	}
}
