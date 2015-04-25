package interviews.phone.onlinestore;

public abstract class Clothing {
	private Double price;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	private String description;	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Clothing(String item, Double cost)	{
		description = item;
		price = cost;
	}
}
