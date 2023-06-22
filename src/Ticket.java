
@SuppressWarnings("serial")
public class Ticket implements java.io.Serializable {
	
	/**
	 * The base cost of the Ticket.
	 */
	private int baseCost;
	
	/**
	 * The type of seat the Ticket is for. Can be "economy",
	 * "premiumEconomy", "business", or "firstClass".
	 */
	private String ticketType;
	
	/**
	 * The Airline the Ticket is for.
	 */
	private Airline airline;
	
	/**
	 * The Flight the Ticket is for
	 */
	private Flight flight;
	
	/**
	 * The time the Passenger booked the Ticket.
	 */
	private long timeBooked;
	
	/**
	 * 
	 */
	private String mealPreference;

	/**
	 * Creates a ticket.
	 * @param baseCost The base cost of the Ticket.
	 * @param ticketType The type of seat the Ticket has.
	 * @param flight The Flight the ticket is from.
	 * @param airline The Airline the ticket is from.
	 * @param timeBooked The time the Ticket was booked.
	 */
	public Ticket(int baseCost, String ticketType, Flight flight, Airline airline, long timeBooked) {
		
		super();
		
		this.baseCost = baseCost;
		this.ticketType = ticketType;
		this.airline = airline;
		this.flight = flight;
		this.timeBooked = timeBooked;
	}
	
	public Ticket(int baseCost, String ticketType, Flight flight, Airline airline, long timeBooked, String mealPreference) {
		
		super();
		
		this.baseCost = baseCost;
		this.ticketType = ticketType;
		this.airline = airline;
		this.flight = flight;
		this.timeBooked = timeBooked;
		this.mealPreference = mealPreference;
	}
	
	
	/**
	 * Gets the baseCost of the Ticket.
	 * @return The baseCost of the Ticket.
	 */
	public int getBaseCost() {
		
		return baseCost;
	}

	/**
	 * Sets the baseCost of the Ticket.
	 * @param The baseCost of the Ticket.
	 */
	public void setBaseCost(int baseCost) {
		
		this.baseCost = baseCost;
	}
	
	/**
	 * Gets the Ticket's type.
	 * @return The Ticket's type.
	 */
	public String getTicketType() {
		
		return ticketType;
	}

	/**
	 * Gets the time the Ticket was booked.
	 * @return The time the Ticket was booked.
	 */
	public long getTimeBooked() {
		
		return timeBooked;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getMealPreference() {
		return mealPreference;
	}

	public void setMealPreference(String mealPreference) {
		this.mealPreference = mealPreference;
	}	
	
	public String toString() {
		String ret = "";
		ret += "-------------------------------------\n";
		ret += "Airline: " + this.airline.getName() + "   Flight Number: " + flight.getFlightNumber() + "\n";
		ret += "From: " + flight.getSource() + "  to: " + flight.getDestination() + "   Departure: " + ((flight.getDepartureTime() - System.currentTimeMillis()) / 60000) 
				+ " minutes from now" + "   Date: " + flight.getDates() + "\n";
		ret += "   Layovers: " + flight.getNumberOfLayovers() + "\n";
		ret += "-------------------------------------";
		return ret;
	}
	
}
