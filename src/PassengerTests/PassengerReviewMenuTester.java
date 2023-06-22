import java.util.ArrayList;

public class PassengerReviewMenuTester {

	public static void main(String[] args) {
		
		Database database = new Database();
		database.getStoredObjects();
		
		Airline delta = new Airline("Delta", "delta", "password");
		Airline united = new Airline("United", "united", "password");
		Airline american = new Airline("American", "american", "password");
		
		Flight flight = new Flight(delta, 123, "Bikini Bottom", "Rock Bottom", 5, System.currentTimeMillis(),
				1, true, "April7,2020", 20, 5, 5, 5, 5);
		Ticket deltaTicket = new Ticket(5, "economy", flight, delta, System.currentTimeMillis());
		ArrayList<Ticket> oldTickets = new ArrayList<Ticket>();
		oldTickets.add(deltaTicket);
		ArrayList<Ticket> noTickets = new ArrayList<Ticket>();
		
		Passenger dherthoge = new Passenger("Dylan", "dherthoge", "password", 0, oldTickets, null, null);
		Passenger jmangan = new Passenger("Mango", "jmangan", "password", 0, noTickets, null, null);
		Passenger pspratt = new Passenger("Phoebe", "pspratt", "password", 0, noTickets, null, null);
//		ArrayList<Passenger> passengers = database.getAllPassengers();
//		passengers.add(dherthoge);
//		passengers.add(jmangan);
//		passengers.add(pspratt);
//		database.setAllPassengers(passengers);
//		Review rDylan = new Review(dherthoge, "Delta", "I loved it", 1);
//		
//		Review rDylan1 = new Review(dherthoge, "United", "I loved it", 2);
//		
//		Review rDylan2 = new Review(dherthoge, "Delta", "I loved it", 4);
//		
//		Review rMango = new Review(jmangan, "American", "I loved it", 3);
//		
//		Review rMango1 = new Review(jmangan, "Delta", "I loved it", 4);
//		
//		Review rMango2 = new Review(jmangan, "Delta", "I loved it", 5);
//		
//		Review rPhoebe = new Review(pspratt, "United", "I loved it", 5);
//		
//		Review rPhoebe1 = new Review(pspratt, "American", "I loved it", 2);
//		
//		Review rPhoebe2 = new Review(pspratt, "Delta", "I loved it", 1);
//		
//		ArrayList<Review> reviews = new ArrayList<Review>();
//		reviews.add(rDylan);
//		reviews.add(rDylan1);
//		reviews.add(rDylan2);
//		reviews.add(rMango);
//		reviews.add(rMango1);
//		reviews.add(rMango2);
//		reviews.add(rPhoebe);
//		reviews.add(rPhoebe1);
//		reviews.add(rPhoebe2);
//		database.setAllReviews(reviews);
//		database.storeObjects();
		

		PassengerReviewMenu pmr = new PassengerReviewMenu(database, jmangan);
		pmr.startMenu();
		System.out.println("Done!");
	}

}
