import java.util.ArrayList;

/**
 * @author Dylan Herthoge
 * Main driver for the entire program.
 */
public class MenuDriver {

	public static void main(String[] args) {
		
		Database database = new Database();
		try {
			
			database.getStoredObjects();
		} catch (Exception e) {}
		
		Passenger dylan = new Passenger("Dylan", "dylan", "p", 20000, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
		Passenger mango = new Passenger("Mango", "mango", "p", 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
		Passenger phoebe = new Passenger("Phoebe", "phoebe", "p", 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
		
		
		Airline delta = new Airline("Delta", "delta", "p");		
		Flight delta1 = new Flight(delta, 111111, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (12 * 60000), 1, true, "May18,2020", 
				4, 1, 1, 1, 1);
		Flight delta2 = new Flight(delta, 222222, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (11 * 60000), 0, false, "May28,2020", 
				4, 1, 1, 1, 1);
		Flight delta3 = new Flight(delta, 333333, "Shell City", "Bikini Bottom", 500, System.currentTimeMillis() + (10 * 60000), 2, true, "May18,2020", 
				1, 1, 0, 0, 0);
		ArrayList<Flight> deltaFlights = new ArrayList<Flight>();
		deltaFlights.add(delta1);
		deltaFlights.add(delta2);
		deltaFlights.add(delta3);
		delta.setAllFlights(deltaFlights);
		
		
		
		Airline american = new Airline("American", "american", "p");
		Flight american1 = new Flight(american, 444444, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (8 * 60000), 0, false, "May18,2020", 
				4, 1, 1, 1, 1);
		Flight american2 = new Flight(american, 555555, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (6 * 60000), 2, true, "May28,2020", 
				4, 1, 1, 1, 1);
		Flight american3 = new Flight(american, 666666, "Shell City", "Bikini Bottom", 2000, System.currentTimeMillis() + (5 * 60000), 2, false, "May18,2020", 
				4, 1, 1, 1, 1);
		ArrayList<Flight> americanFlights = new ArrayList<Flight>();
		americanFlights.add(american1);
		americanFlights.add(american2);
		americanFlights.add(american3);
		american.setAllFlights(americanFlights);
		
		
		
		Airline united = new Airline("United", "united", "p");
		Flight united1 = new Flight(united, 777777, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (4 * 60000), 1, false, "May18,2020", 
				4, 1, 1, 1, 1);
		Flight united2 = new Flight(united, 888888, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (3 * 60000), 0, false, "May28,2020", 
				4, 1, 1, 1, 1);
		Flight united3 = new Flight(united, 999999, "Shell City", "Bikini Bottom", 500, System.currentTimeMillis() + (1 * 60000), 0, true, "May18,2020", 
				4, 1, 1, 1, 1);
		ArrayList<Flight> unitedFlights = new ArrayList<Flight>();
		unitedFlights.add(united1);
		unitedFlights.add(united2);
		unitedFlights.add(united3);
		united.setAllFlights(unitedFlights);
		
		
		
		Review rDylan = new Review(dylan, "Delta", "I loved it", 1);	
		Review rDylan1 = new Review(dylan, "United", "I loved it", 2);		
		Review rDylan2 = new Review(dylan, "Delta", "I loved it", 4);		
		Review rMango = new Review(mango, "American", "I loved it", 3);		
		Review rMango1 = new Review(mango, "Delta", "I loved it", 4);		
		Review rMango2 = new Review(mango, "Delta", "I loved it", 5);		
		Review rPhoebe = new Review(phoebe, "United", "I loved it", 5);		
		Review rPhoebe1 = new Review(phoebe, "American", "I loved it", 2);		
		Review rPhoebe2 = new Review(phoebe, "Delta", "I loved it", 1);
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		
		
		
		ArrayList<Airline> allAirlines = new ArrayList<Airline>();
		allAirlines.add(delta);
		allAirlines.add(american);
		allAirlines.add(united);
		
		ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();
		allPassengers.add(dylan);
		allPassengers.add(mango);
		allPassengers.add(phoebe);
		
		ArrayList<Flight> allFlights = new ArrayList<Flight>();
		allFlights.add(delta1);
		allFlights.add(delta2);
		allFlights.add(delta3);
		allFlights.add(american1);
		allFlights.add(american2);
		allFlights.add(american3);
		allFlights.add(united1);
		allFlights.add(united2);
		allFlights.add(united3);
		
		
		
		database.setAllPassengers(allPassengers);
		database.setAllAirlines(allAirlines);
		database.setAllFlights(allFlights);
		database.setAllReviews(reviews);
		
		
		database.storeObjects();
		
		Menu mainMenu = new Menu(database);
		mainMenu.startMenu();
	}
	
}
