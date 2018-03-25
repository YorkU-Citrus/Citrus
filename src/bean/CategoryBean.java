package bean;

public class CategoryBean {
	private int cid;
	private String ctitle;


	public CategoryBean(int cid) {
		super();
		this.cid = cid;
	}
	
	public CategoryBean(int cid, String ctitle) {
		super();
		this.cid = cid;
		this.ctitle = ctitle;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
}
