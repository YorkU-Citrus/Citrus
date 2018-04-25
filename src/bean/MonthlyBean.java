package bean;

public class MonthlyBean {
	/**
	 * Declarations of Global
	 */
	private int year;
	private int month;
	private int salesVolumn;
	/**
	 * To String Method
	 */
	@Override
	public String toString() {
		return "MonthlyBean [year=" + year + ", month=" + month + ", salesVolumn=" + salesVolumn + "]";
	}
	/**
	 * Constructor To Store Monthly Object
	 * @param year
	 * @param month
	 * @param salesVolumn
	 */
	public MonthlyBean(int year, int month, int salesVolumn) {
		super();
		this.year = year;
		this.month = month;
		this.salesVolumn = salesVolumn;
	}
	/**
	 * Returns the Year
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Sets the Year
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Returns the Month
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * Sets the month
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * Returns the Sales Volume
	 * @return salesVolumn
	 */
	public int getSalesVolumn() {
		return salesVolumn;
	}
	/**
	 * Sets the Sales Volume
	 * @param salesVolumn
	 */
	public void setSalesVolumn(int salesVolumn) {
		this.salesVolumn = salesVolumn;
	}
	
	
}
