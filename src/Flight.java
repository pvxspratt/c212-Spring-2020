import java.util.ArrayList;

@SuppressWarnings("serial")
public class Flight implements java.io.Serializable {
	/**
	 * The Airline the flight is for.
	 */
	private Airline airline;
	
	/**
	 * The Flight's number.
	 */
	private int flightNumber;
	
	/**
	 * Where the Flight is from.
	 */
	private String source;
	
	/**
	 * Where the flight is going.
	 */
	private String destination;
	
	/**
	 * The miles it takes to get from the source to the destination.
	 */
	private int miles;
	
	/**
	 * When the flight is departing.
	 */
	private long departureTime;
	
	/**
	 * Number of lay overs.
	 */
	private int numberOfLayovers;
	
	/**
	 * True if flight is a round trip, false if the flight is one-way.
	 */
	private boolean roundOrOne; 
	
	/**
	 * Date of the Flight.
	 */
	private String dates;
	
	/**
	 * List of passengers on the flight.
	 */
	private ArrayList<Passenger> passengers;
	
	/**
	 * Total seats on the flight.
	 */
	private int totalSeats;
	
	/**
	 * Number of first class seats.
	 */
	private int firstClassSeats;
	
	/**
	 * Number of business class seats.
	 */
	private int businessClassSeats;
	
	/**
	 * Number of premium economy class seats.
	 */
	private int premiumEconomyClassSeats;
	
	/**
	 * Number of economy class seats.
	 */
	private int economyClassSeats;

	
	/**
	 * Constructs the Flight.
	 */
	public Flight(Airline airline, int flightNumber, String source, String destination, int miles, long departureTime,
			int numberOfLayovers, boolean roundOrOne, String dates, int totalSeats, 
			int firstClassSeats, int businessClassSeats, int premiumEconomyClassSeats, int economyClassSeats) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.miles = miles;
		this.departureTime = departureTime;
		this.numberOfLayovers = numberOfLayovers;
		this.roundOrOne = roundOrOne;
		this.dates = dates;
		this.passengers = new ArrayList<Passenger>();
		this.totalSeats = totalSeats;
		this.firstClassSeats = firstClassSeats;
		this.businessClassSeats = businessClassSeats;
		this.premiumEconomyClassSeats = premiumEconomyClassSeats;
		this.economyClassSeats = economyClassSeats;
	}

	//Getters and setters 
	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	public int getFlightNumber() {
		return flightNumber;
	}
	
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(long time) {
		this.departureTime = time;
	}

	public int getNumberOfLayovers() {
		return numberOfLayovers;
	}

	public void setNumberOfLayovers(int numberOfLayovers) {
		this.numberOfLayovers = numberOfLayovers;
	}

	/**
	 * Returns the string for a round trip or a one-way trip.
	 * @return round trip or one-way
	 */
	public String stringRoundOrOne() {
		if(isRoundOrOne()) {
			return "Round Trip";
		} else {
			return "One Way";
		}
	}
	
	public boolean isRoundOrOne() {
		return roundOrOne;
	}

	public void setRoundOrOne(boolean roundOrOne) {
		this.roundOrOne = roundOrOne;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(int firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}
	
	public int getBusinessClassSeats() {
		return businessClassSeats;
	}

	public void setBusinessClassSeats(int businessClassSeats) {
		this.businessClassSeats = businessClassSeats;
	}
	
	public int getPremiumEconomyClassSeats() {
		return premiumEconomyClassSeats;
	}

	public void setPremiumEconomyClassSeats(int premiumEconomyClassSeats) {
		this.premiumEconomyClassSeats = premiumEconomyClassSeats;
	}
	
	public int getEconomyClassSeats() {
		return economyClassSeats;
	}

	public void setEconomyClassSeats(int economyClassSeats) {
		this.economyClassSeats = economyClassSeats;
	}	
	
	/**
	 * Returns a string with all the information of the flight that the Airline put in.
	 * @return flight information
	 */
	public String toAirlineString() {
		String ret = "";
		ret += "-------------------------------------\n";
		ret += "Airline: " + this.airline.getName() + "   Flight Number: " + flightNumber + "\n";
		ret += "From: " + source + "  to: " + destination + "   Departure: " + ((departureTime - System.currentTimeMillis()) / 60000) 
				+ " minutes from now" + "   Date: " + dates + "\n";
		ret += stringRoundOrOne() + "   Layovers: " + this.numberOfLayovers + "\n";
		ret += "Total Seats: " + totalSeats + "  F: " + firstClassSeats + "  B: " + businessClassSeats + 
												"  PE: " + premiumEconomyClassSeats + "  E: " + economyClassSeats + "\n";
		ret += "-------------------------------------";
		return ret;
	}
	
	/**
	 * Returns a string with all the information the passengers need to see.
	 * @return flight information
	 */
	public String toPassengerString() {
		String ret = "";
		ret += source + " to " + destination + "   " + this.airline.getName();
		ret += "Bording Time: " + "15 before " + ((departureTime - System.currentTimeMillis()) / 60000) + " minutes from now" + "   Date: " + dates;
		ret += stringRoundOrOne() + "   Layovers: " + numberOfLayovers;
		return ret;
	}
	
}
