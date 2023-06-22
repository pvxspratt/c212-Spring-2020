
/**
 * @author Dylan Herthoge
 * A review for an Airline.
 */
@SuppressWarnings("serial")
public class Review implements java.io.Serializable {
	
	/**
	 * The Passenger that left the Review.
	 */
	private Passenger passenger;
	
	/**
	 * The Airline the review is for.
	 */
	private String airline;
	
	/**
	 * What the Passenger said about the Airline.
	 */
	private String comments;
	
	/**
	 * The rating the Passenger gave the Airline.
	 */
	private int stars;
	
	/**
	 * Constructs a Review from the passenger
	 * @param passenger the Passenger who wrote the review, hidden from Airline
	 * @param airline the Airline the Passenger is making the review on
	 * @param comments comments Passenger makes
	 * @param stars stars the Passenger gives to an Airline
	 */
	public Review(Passenger passenger, String airline,  String comments, int stars) {
		
		this.passenger = passenger;
		this.airline = airline;
		this.comments = comments;
		this.stars = stars;
	}

	//Getters and setters
	public Passenger getPassenger() {
		
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		
		this.passenger = passenger;
	}

	public String getAirlineName() {
		
		return airline;
	}

	public void setAirlineName(String airline) {
		
		this.airline = airline;
	}

	public String getComments() {
		
		return comments;
	}

	public void setComments(String comments) {
		
		this.comments = comments;
	}

	public int getStars() {
		
		return stars;
	}

	public void setStars(int stars) {
		
		this.stars = stars;
	}
	
	/**
	 * Returns a String representation of this.
	 */
	public String toString() {
		
		return "Passenger: " + this.passenger.getName() + "\nAirline: " + this.airline + "\nStars: " + this.stars + "\n\n" + this.comments;
	}
	
}
