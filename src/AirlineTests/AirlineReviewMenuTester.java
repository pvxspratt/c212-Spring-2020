import java.util.ArrayList;

public class AirlineReviewMenuTester {

	public static void main(String[] args) {

		Database database = new Database();
		database.getStoredObjects();

		Airline delta = new Airline("Delta", "delta", "password");
		Airline united = new Airline("United", "united", "password");
		Airline american = new Airline("American", "american", "password");
		ArrayList<Airline> airlines = database.getAllAirlines();
		airlines.add(delta);
		airlines.add(united);
		airlines.add(american);
		database.setAllAirlines(airlines);
		database.storeObjects();

		AirlineReviewMenu amr = new AirlineReviewMenu(database, delta);
		amr.startMenu();
		System.out.println("Done!");
	}

}
