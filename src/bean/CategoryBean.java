package bean;

public class CategoryBean {
	/**
	 * Declaration of Globals
	 */
	private int cid;
	private String ctitle;

	/**
	 * Constructor
	 * @param cid
	 */
	public CategoryBean(int cid) {
		super();
		this.cid = cid;
	}
	/**
	 * To string method.
	 */
	@Override
	public String toString() {
		return String.format(
				"CategoryBean [categoryId=%s, categoryTitle=%s]",
				cid, ctitle);
	}
	/**
	 * Constructor To store category Object with category id and category title as attribute 
	 * @param cid
	 * @param ctitle
	 */
	public CategoryBean(int cid, String ctitle) {
		super();
		this.cid = cid;
		this.ctitle = ctitle;
	}
	/**
	 * Returns the Category Id
	 * @return cid
	 */
	public int getCid() {
		return cid;
	}
	/**
	 * Sets the Category Id
	 * @param cid
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	/**
	 * Returns the Category Title
	 * @return ctitle
	 */
	public String getCtitle() {
		return ctitle;
	}
	/**
	 * Sets the Category Title
	 * @param ctitle
	 */
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
}
