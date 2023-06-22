@SuppressWarnings("serial")
public class PassengerFlightNeeds implements java.io.Serializable {
	
	
	/**
	 * The Passenger's preferred meal.
	 */
	private String mealPreference;
	
	/**
	 * Where they want to take off.
	 */
	private String source;
	
	/**
	 * Where they want to land.
	 */
	private String destination;
	
	/**
	 * The maximum price they want to pay.
	 */
	private int cost;
	
	/**
	 * The time they want to take off.
	 */
	private long time;
	
	/**
	 * The dates they want the flight to be formatted as "MM/DD/YYYY,MM/DD/YYYY,etc..."
	 */
	private String dates;
	
	/**
	 * Constructs the a PassengerFlightNeeds to store a Passenger's preferences.
	 * @param mealPreference The Passenger's preferred meal.
	 * @param source Where they want to take off.
	 * @param destination Where they want to land.
	 * @param cost The maximum price they want to pay.
	 * @param time The time they want to take off.
	 * @param dates The dates they want the flight to be formatted as "MM/DD/YYYY,MM/DD/YYYY,etc..."
	 */
	public PassengerFlightNeeds(String mealPreference, String source, String destination, int cost, long time, String dates) { 
		
		this.mealPreference = mealPreference;
		this.source = source; 
		this.destination = destination;
		this.cost = cost;
		this.time = time;  
		this.dates = dates;
	}

	//Getters and setters
	public String getMealPreference() {
		
		return mealPreference;
	}

	public void setMealPreference(String mealPreference) {
		
		this.mealPreference = mealPreference;
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

	public int getCost() {
		
		return cost;
	}

	public void setCost(int cost) {
		
		this.cost = cost;
	}

	public long getTime() {
		
		return time;
	}

	public void setTime(int time) {
		
		this.time = time;
	}

	public String getDates() {
		
		return dates;
	}

	public void setDates(String dates) {
		
		this.dates = dates;
	}
	
	/**
	 * Returns the cost as a string.
	 * @return the cost as a string
	 */
	public String costToString() {
		
		return "$" + cost;
	}
	
	/**
	 * Returns the time as a string.
	 * @return the time as a string
	 */
	public String timeToString() {
		
		return "time as a string";
	}
	
}
