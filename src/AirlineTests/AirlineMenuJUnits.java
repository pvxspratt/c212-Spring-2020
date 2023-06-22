package finalProject;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class AirlineMenuJUnits {
	Airline lufthansa = new Airline("Lufthansa", "luffy", "germs");
	Airline swiss = new Airline("Swiss International Air Lines", "swiss", "thePrettySwitzers");
	Airline alita = new Airline("Alitalia", "alitty", "veniceIsTrash");

	@Test
	void Test() {
		Database db = new Database();
		AirlineMenu menu = new AirlineMenu(db, lufthansa);
	
		Flight expectedFlight = new Flight(lufthansa, 699476, "Chicago", "Oslo", 4053, 1400, 0, true, "November 3, 2021", 340, 8, 92, 32, 208);
		Flight expectedFlight2 = new Flight(swiss, 809885, "Los Angeles", "Florence", 6213, 1600, 0, true, "April 26, 2021", 236, 8, 30, 21, 177);
		Flight expectedFlight3 = new Flight(alita, 146646, "New York City", "Berlin", 3982, 1200, 0, false, "September 6, 2022", 516, 9, 80, 38, 389);
		
		assertNotEquals(expectedFlight, menu.makeFlight(alita, 146646, "New York City", "Berlin", 3982, 1200, 0, false, "September 6, 2022", 516, 9, 80, 38, 389));
		assertNotEquals(expectedFlight2, menu.makeFlight(alita, 146646, "New York City", "Berlin", 3982, 1200, 0, false, "September 6, 2022", 516, 9, 80, 38, 389));
		assertNotEquals(expectedFlight3, menu.makeFlight(lufthansa, 699476, "Chicago", "Oslo", 4053, 1400, 0, true, "November 3, 2021", 340, 8, 92, 32, 208));
	}
}
