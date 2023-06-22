import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Airline implements java.io.Serializable {
	/**
	 * The name of the Airline.
	 */
	private String name;
	
	/**
	 * The Airline's user name.
	 */
	private String username;
	
	/**
	 * The Airline's password.
	 */
	private String password;
	
	/**
	 * A list of all the blacklisted Passengers for this Airline.
	 */
	private ArrayList<Passenger> blackList;
	
	/**
	 * A list of all flights for this Airline.
	 */
	private ArrayList<Flight> allFlights;
	
	/**
	 * Constructs the Airline.
	 */
	public Airline(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.blackList = new ArrayList<>();
		this.allFlights = new ArrayList<>();
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a list of the blacklisted Passengers' names.
	 * @return all the names of the blacklisted Passengers
	 */
	public ArrayList<String> getBlackListNames() {
		ArrayList<String> blackListNames = new ArrayList<>();
		
		for(Passenger p : blackList) {
			blackListNames.add(p.getName());
		}
		return blackListNames;
	}
	
	public ArrayList<Passenger> getBlackList() {
		return blackList;
	}

	public void setBlackList(ArrayList<Passenger> blackList) {
		this.blackList = new ArrayList<>();
	}

	/**
	 * Takes in a list of Flights and returns a list of each flight's flightNumber.
	 * @param plannedFlights a list of Flights
	 * @return a list of flightNumbers
	 */
	public ArrayList<Integer> getStringFlightList(ArrayList<Flight> plannedFlights) {
		ArrayList<Integer> stringFlightList = new ArrayList<>();
		
		for(Flight f : plannedFlights) {
			stringFlightList.add(f.getFlightNumber());
		}
		return stringFlightList;
	}
	
	/**
	 * Takes in a list of Flights and returns a list of each flight's flightNumber, source, and destination.
	 * @param allFlights a list of Flights
	 * @return list of strings with flightNumbers, sources, and destinations
	 */
	public ArrayList<String> getAllFlightsAsStrings(ArrayList<Flight> allFlights) {
		ArrayList<String> allStrings = new ArrayList<>();
		
		for(Flight k : allFlights) {
			allStrings.add(k.getFlightNumber() + ": " + k.getSource() + " to " + k.getDestination());
		}
		return allStrings;
	}

	public ArrayList<Flight> getAllFlights() {
		return allFlights;
	}

	public void setAllFlights(ArrayList<Flight> allFlights) {
		this.allFlights = allFlights;
	}
	
	/**
	 * Returns the flight in the list that corresponds to the flight number.
	 * @param flightNumber the flightNumber
	 * @param flights a list of flights
	 * @return the flight with that has the flightNumber
	 */
	public Flight getCorrespondingFlight(int flightNumber, ArrayList<Flight> flights) {
		ArrayList<Flight> flightList = flights;
		Flight correspondingFlight = null;
		
		for(Flight g : flightList) {
			if(g.getFlightNumber() == flightNumber) {
				correspondingFlight = g;
			}
		}
		return correspondingFlight;
	}
	
	/**
	 * Checks if a flight with the given flightNumber exists. If it doesn't it returns the number, 
	 * if it does it generates a new random number and checks again using recursion.
	 * @param flightNumber 
	 * @return a flightNumber
	 */
	public int flightExists(int flightNumber) {
		int newFlightNumber = flightNumber;
		Random random = new Random();
		
		ArrayList<Integer> allFlightsFlightNumbers = new ArrayList<Integer>();
		for(Flight n : allFlights) {
			allFlightsFlightNumbers.add(n.getFlightNumber());
		}
		
		for(Integer e : allFlightsFlightNumbers) {
				
			if(allFlightsFlightNumbers.contains(e)) {
				newFlightNumber = random.nextInt(1000000);
			}
		}
		
		return newFlightNumber;
	}	
	
}
