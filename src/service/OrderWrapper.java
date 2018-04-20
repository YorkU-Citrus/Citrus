package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bean.OrderBean;


@XmlRootElement(name="orderListType")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderWrapper {

	@XmlElement	(name="orderList")
	public List<OrderBean> list;
	
	public OrderWrapper() {
		this.list = new ArrayList<OrderBean>();
	}
}
