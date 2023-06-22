import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PassengerMenuJUnits {
	Database database = new Database();
	
	
	Passenger dherthoge = new Passenger("Dylan", "dylan", "p", 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
	Passenger jmangan = new Passenger("Mango", "mango", "p", 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
	Passenger pspratt = new Passenger("Phoebe", "phoebe", "p", 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(), null);
	
	Airline delta = new Airline("Delta", "delta", "p");		
	Flight delta1 = new Flight(delta, 111111, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (10 * 60000), 1, true, "May18,2020", 
			4, 1, 1, 1, 1);
	Flight delta2 = new Flight(delta, 222222, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (5 * 60000), 0, false, "May28,2020", 
			4, 1, 1, 1, 1);
	Flight delta3 = new Flight(delta, 333333, "Shell City", "Bikini Bottom", 500, System.currentTimeMillis() + (10 * 60000), 2, true, "May18,2020", 
			1, 1, 0, 0, 0);
	ArrayList<Flight> deltaFlights = new ArrayList<Flight>();
	
	Airline american = new Airline("American", "american", "p");
	Flight american1 = new Flight(american, 444444, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (10 * 60000), 0, false, "May18,2020", 
			4, 1, 1, 1, 1);
	Flight american2 = new Flight(american, 555555, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (5 * 60000), 2, true, "May28,2020", 
			4, 1, 1, 1, 1);
	Flight american3 = new Flight(american, 666666, "Shell City", "Bikini Bottom", 2000, System.currentTimeMillis() + (10 * 60000), 2, false, "May18,2020", 
			4, 1, 1, 1, 1);
	ArrayList<Flight> americanFlights = new ArrayList<Flight>();
	
	Airline united = new Airline("United", "united", "p");
	Flight united1 = new Flight(united, 777777, "Bikini Bottom", "Rock Bottom", 500, System.currentTimeMillis() + (10 * 60000), 1, false, "May18,2020", 
			4, 1, 1, 1, 1);
	Flight united2 = new Flight(united, 888888, "Rock Bottom", "Shell City", 2000, System.currentTimeMillis() + (5 * 60000), 0, false, "May28,2020", 
			4, 1, 1, 1, 1);
	Flight united3 = new Flight(united, 999999, "Shell City", "Bikini Bottom", 500, System.currentTimeMillis() + (10 * 60000), 0, true, "May18,2020", 
			4, 1, 1, 1, 1);
	ArrayList<Flight> unitedFlights = new ArrayList<Flight>();
	
	ArrayList<Airline> allAirlines = new ArrayList<Airline>();
	
	ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();
	
	ArrayList<Flight> allFlights = new ArrayList<Flight>();

	@Test
	public void test() {
		// Initializes all flights
		deltaFlights.add(delta1);
		deltaFlights.add(delta2);
		deltaFlights.add(delta3);
		delta.setAllFlights(deltaFlights);
		
		americanFlights.add(american1);
		americanFlights.add(american2);
		americanFlights.add(american3);
		american.setAllFlights(americanFlights);
		
		unitedFlights.add(united1);
		unitedFlights.add(united2);
		unitedFlights.add(united3);
		united.setAllFlights(unitedFlights);
		
		// adds all airlines
		allAirlines.add(delta);
		allAirlines.add(american);
		allAirlines.add(united);
		
		// adds all passengers
		allPassengers.add(dherthoge);
		allPassengers.add(jmangan);
		allPassengers.add(pspratt);
		
		// adds all flights
		allFlights.add(delta1);
		allFlights.add(delta2);
		allFlights.add(delta3);
		allFlights.add(american1);
		allFlights.add(american2);
		allFlights.add(american3);
		allFlights.add(united1);
		allFlights.add(united2);
		allFlights.add(united3);
		
		// Initialize database
		database.setAllPassengers(allPassengers);
		database.setAllAirlines(allAirlines);
		database.setAllFlights(allFlights);
		
		// make Passenger Menu
		PassengerMenu pm = new PassengerMenu(database, jmangan);	
		
		// filterFlights(Set<String> filters) 
		Set<String> filters = new HashSet<String>();
		filters.add("Airline:delta");
		pm.filterFlights(filters);
		String flights = "";
		ArrayList<Flight> filterFlights = pm.getFilteredFlights();
		for(int i=0; i<filterFlights.size(); i++) {
			flights += filterFlights.get(i).toPassengerString();
		}
		
		assertEquals("Bikini Bottom to Rock Bottom   DeltaBording Time: 15 before 9 minutes "
				+ "from now   Date: May18,2020Round Trip   Layovers: 1Rock Bottom to Shell City   "
				+ "DeltaBording Time: 15 before 4 minutes from now   Date: May28,2020One Way   "
				+ "Layovers: 0Shell City to Bikini Bottom   DeltaBording Time: 15 before 9 minutes "
				+ "from now   Date: May18,2020Round Trip   Layovers: 2",flights);
		
		// sortFlights(String howToSort)
		ArrayList<Flight> sortedFlights = pm.sortFlights("destination");
		String flights2 = "";
		for(int i=0; i<sortedFlights.size(); i++) {
			flights2 += sortedFlights.get(i).toPassengerString();
		}
		assertEquals("Shell City to Bikini Bottom   DeltaBording Time: 15 before 9 minutes from "
				+ "now   Date: May18,2020Round Trip   Layovers: 2Bikini Bottom to Rock Bottom   "
				+ "DeltaBording Time: 15 before 9 minutes from now   Date: May18,2020Round Trip   "
				+ "Layovers: 1Rock Bottom to Shell City   DeltaBording Time: 15 before 4 minutes "
				+ "from now   Date: May28,2020One Way   Layovers: 0", flights2);
		
		
		// calculateFlightCost(Flight flight, String classSeat, int bags)
		int cost = pm.calculateFlightCost(united1, "economy", 0);
		assertEquals(600,cost);
		cost = pm.calculateFlightCost(united1, "premiumEconomy", 1);
		assertEquals(686,cost);
		cost = pm.calculateFlightCost(united1, "business", 2);
		assertEquals(858,cost);
		cost = pm.calculateFlightCost(united1, "firstClass", 3);
		assertEquals(1667,cost);
		
		// makeTicket()
		
	}

}
