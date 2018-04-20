package bean;

public class MonthlyBean {
	private int year;
	private int month;
	private int salesVolumn;
	
	@Override
	public String toString() {
		return "MonthlyBean [year=" + year + ", month=" + month + ", salesVolumn=" + salesVolumn + "]";
	}
	
	public MonthlyBean(int year, int month, int salesVolumn) {
		super();
		this.year = year;
		this.month = month;
		this.salesVolumn = salesVolumn;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getSalesVolumn() {
		return salesVolumn;
	}
	public void setSalesVolumn(int salesVolumn) {
		this.salesVolumn = salesVolumn;
	}
	
	
}
