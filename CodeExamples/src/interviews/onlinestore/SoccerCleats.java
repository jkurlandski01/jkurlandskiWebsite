package interviews.onlinestore;

public class SoccerCleats extends Clothing implements SportsGear {

	public SoccerCleats(String item, Double cost) {
		super(item, cost);
	}

	@Override
	public Sport getSport() {
		return Sport.SOCCER;
	}
	
	public static void main(String[] args) {
		SoccerCleats cleats = new SoccerCleats("Size 8", 54.99);
		System.out.println(cleats.getDescription());
		System.out.println(cleats.getPrice());
		System.out.println(cleats.getSport());
	}

}
