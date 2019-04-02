import java.awt.Graphics;

public class Information {
	
	//fields from file
	private String date;
	private String name;
	private String company;
	private String color;
	
	//constructor to store data from file
	public Information(String date, String name, String company, String color) {
		this.date = date;
		this.name = name;
		this.company = company;
		this.color = color;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getColor() {
		return color;
	}

	
	public void render(Graphics g, int x, int y) {
		
	}

	@Override
	public String toString() {
		return "Information [date=" + date + ", name=" + name + ", company=" + company + ", color=" + color + "]";
	}
	
	

}