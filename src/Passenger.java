import java.util.ArrayList;

@SuppressWarnings("serial")
public class Passenger implements java.io.Serializable {

	/**
	 * The first and last name of the Passenger.
	 */
	private String name;
	
	/**
	 * The username of the Passenger.
	 */
	private String username;
	
	/**
	 * The password of the user.
	 */
	private String password;
	
	/**
	 * The numberOfMiles that have been gifted to our passenger.
	 */
	private int numberOfMiles;
	
	/**
	 * All of the Tickets the Passenger has used.
	 */
	private ArrayList<Ticket> oldTickets;
	
	/**
	 * All of the Tickets the Passenger has booked but has not used.
	 */
	private ArrayList<Ticket> currentTickets;

	/**
	 * The Passenger's flight needs.
	 */
	private PassengerFlightNeeds flightPreferences;
	
	public Passenger(String name, String username, String password, int numberOfMiles, ArrayList<Ticket> oldTickets,
			ArrayList<Ticket> currentTickets, PassengerFlightNeeds flightPreferences) {
		
		super();
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.numberOfMiles = numberOfMiles;
		this.oldTickets = oldTickets;
		this.currentTickets = currentTickets;
		this.flightPreferences = flightPreferences;
	}

	/**
	 * Gets the name of the Passenger.
	 * @return The name of the Passenger.
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Sets the name of the Passenger.
	 * @param The name of the Passenger.
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Gets the username of the Passenger.
	 * @return The username of the Passenger.
	 */
	public String getUsername() {
		
		return username;
	}

	/**
	 * Sets the username of the Passenger.
	 * @param The username of the Passenger.
	 */
	public void setUsername(String username) {
		
		this.username = username;
	}

	/**
	 * Gets the password of the Passenger.
	 * @return The password of the Passenger.
	 */
	public String getPassword() {
		
		return password;
	}

	/**
	 * Sets the password of the Passenger.
	 * @param The password of the Passenger.
	 */
	public void setPassword(String password) {
		
		this.password = password;
	}

	/**
	 * Gets the number of miles the Passenger has.
	 * @return The number of miles the Passenger has.
	 */
	public int getNumberOfMiles() {
		
		return numberOfMiles;
	}

	/**
	 * Sets the number of miles the Passenger has.
	 * @param The number of miles the Passenger has.
	 */
	public void setNumberOfMiles(int numberOfMiles) {
		
		this.numberOfMiles = numberOfMiles;
	}

	/**
	 * Gets the Passenger's old Tickets.
	 * @return The Passenger's old Tickets.
	 */
	public ArrayList<Ticket> getOldTickets() {
		
		return oldTickets;
	}

	/**
	 * Sets the Passenger's old Tickets.
	 * @param The Passenger's old Tickets.
	 */
	public void setOldTickets(ArrayList<Ticket> oldTickets) {
		
		this.oldTickets = oldTickets;
	}

	/**
	 * Gets the Passenger's current Tickets.
	 * @return The Passenger's current Tickets.
	 */
	public ArrayList<Ticket> getCurrentTickets() {
		
		return currentTickets;
	}

	/**
	 * Sets the Passenger's current Tickets.
	 * @param The Passenger's current Tickets.
	 */
	public void setCurrentTickets(ArrayList<Ticket> currenTickets) {
		this.currentTickets = currenTickets;
	}

	/**
	 * Gets the Passenger's flight preferences.
	 * @return The Passenger's flight preferences.
	 */
	public PassengerFlightNeeds getFlightPreferences() {
		return flightPreferences;
	}

	/**
	 * Sets the Passenger's flight preferences.
	 * @param The Passenger's flight preferences.
	 */
	public void setFlightPreferences(PassengerFlightNeeds flightPreferences) {
		this.flightPreferences = flightPreferences;
	}
	
}
