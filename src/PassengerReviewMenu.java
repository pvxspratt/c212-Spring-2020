import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Dylan Herthoge
 * Allows the Passenger to leave a Review for any Flight they have taken.
 */
public class PassengerReviewMenu {

	/**
	 * A filtered set of Reviews to display to the current Passenger.
	 */
	private ArrayList<Review> filteredReviews;
	
	/**
	 * An instance of the Database
	 */
	private Database database;
	
	/**
	 * The current Passenger using the system.
	 */
	private Passenger currentPassenger;
	
	/**
	 * The Scanner for this class.
	 */
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Creates a PassengerReviewMenu.
	 */
	public PassengerReviewMenu(Database database, Passenger passenger) {
		
		super();
		
		this.database = database;
		this.currentPassenger = passenger;
		
		this.filteredReviews = database.getAllReviews();
	}

	/**
	 * Only used for testing.
	 * @param reviews An ArrayList<Review>.
	 */
	protected void setReviews(ArrayList<Review> reviews) {
		
		this.filteredReviews = reviews;
	}
	
	/**
	 * Allows the passenger to leave a Review for a Flight
	 * @param passenger The Passenger leaving the Review.
	 * @param flight The Flight the Review is for.
	 * @param Comments The comments the user has for the Flight (as a String).
	 * @param stars An int representing the amount of stars the Passenger would
	 * 		 		 give the flight.
	 */
	private Review addReview() {

		System.out.print("\n\nPlease enter the full name of the Airline: ");
		String airline = scanner.nextLine();

	// Checks to make sure this customer has flown with the Airline they're trying to review
		
		if (!this.checkPreviousFlights(airline)) {
			
			return null;
		}
	// If they have flown with that Airline, lets them make the review
		else {

			String starsMessage = "Please enter the name of the amount of stars (1-5) you would give the Airline: ";
			System.out.print(starsMessage);
			
	      int stars = -1;
	   	
	   	while(true) {
	   		
	   	// If the input for stars is not an int or is out of the acceptable range, throws an exception
	   	   try {
	   	   	stars = Integer.parseInt(scanner.nextLine().trim());
	   	   	
	   	   	if (stars > 5 || stars < 1) {

						throw new NumberFormatException();
					}
	   	      break;
	   	   }
	   	// If the user does not enter an integer, asks for an integer again
	   	   catch(NumberFormatException e) {
	   	   	
	   	   	System.out.println("Stars must be an int between 1 and 5!\n" + starsMessage);
	   	   }
	   	}
			
			System.out.print("Please enter the comments you have for the Airline: ");
			String comments = scanner.nextLine();
		
		// Makes a new Review with the given info and returns it
			return new Review(this.currentPassenger, airline, comments, stars);

		}
	}
	
	/**
	 * Checks if this passenger has flown with the given Airline.
	 * @param airline The airline this passenger is string to review.
	 * @return Whether or not they have flown with the given Airline.
	 */
	protected boolean checkPreviousFlights(String airline) {
		
		ArrayList<Ticket> oldTickets = this.currentPassenger.getOldTickets();
		boolean haveFlown = false;
		
	// Iterates through the current passenger's old tickets to decide if they have flow with the Airline they are trying to review
		for (int i = 0; i < oldTickets.size(); i++) {
			
			if (oldTickets.get(i).getAirline().getName().toLowerCase().trim().equals(airline.toLowerCase().trim())) {
				
				haveFlown = true;
			}
		}
		
		return haveFlown;
	}

	/**
	 * Filters all of the instance Reviews by the given Airline name.
	 * @param airline The name of the Airline to filter by.
	 * @return The reviews that meet the requirement.
	 */
	protected ArrayList<Review> filterByAirline(String airline) {
		
	// Creates a copy to filter
		ArrayList<Review> copy = new ArrayList<Review>();
		
		for (int i = 0; i < this.filteredReviews.size(); i++) {
			
			copy.add(this.filteredReviews.get(i));
		}
			
		ArrayList<Integer> positionsToRemove = new ArrayList<Integer>();
	// Adds the positions to delete to an ArrayList
		for (int i = 0; i < copy.size(); i++) {
				
			if (!copy.get(i).getAirlineName().toLowerCase().trim().equals(airline.toLowerCase().trim())) {

				positionsToRemove.add(i);
			}
		}
				
	// Removes those positions to not disturb the order of this.filteredReviews
		for (int i = positionsToRemove.size() - 1; i >= 0 ; i--) {
					
			copy.remove((int) positionsToRemove.get(i));
		}
				
		return copy;	
	}
	
	/**
	 * Filters all of the instance Reviews by the given Passenger name.
	 * @param passenger The name of the Passenger to filter by.
	 * @return The reviews that meet the requirement.
	 */
	protected ArrayList<Review> filterByPassenger(String passenger) {
		
	// Creates a copy to filter
		ArrayList<Review> copy = new ArrayList<Review>();
			
		for (int i = 0; i < this.filteredReviews.size(); i++) {
				
			copy.add(this.filteredReviews.get(i));
		}

		ArrayList<Integer> positionsToRemove = new ArrayList<Integer>();
	// Adds the positions to delete to an ArrayList
		for (int i = 0; i < copy.size(); i++) {
			
			if (!copy.get(i).getPassenger().getName().toLowerCase().trim().equals(passenger.toLowerCase().trim())) {
				
				positionsToRemove.add(i);
			}
		}
		
	// Removes those positions to not disturb the order of this.filteredReviews
		for (int i = positionsToRemove.size() - 1; i >= 0 ; i--) {
			
			copy.remove((int) positionsToRemove.get(i));
		}
		
		return copy;
	}

	/**
	 * Filters all of the instance Reviews by the given number of Stars.
	 * @param stars The name of the number of Stars to filter by.
	 * @param aboveOrBelow Whether or not to show stars above or below the given number of stars.
	 * @return The reviews that meet the requirement.
	 */
	protected ArrayList<Review> filterByStars(int stars, String aboveOrBelow) {
		
	// Creates a copy to filter
		ArrayList<Review> copy = new ArrayList<Review>();
				
		for (int i = 0; i < this.filteredReviews.size(); i++) {
					
			copy.add(this.filteredReviews.get(i));
		}
		
		ArrayList<Integer> positionsToRemove = new ArrayList<Integer>();
		// Adds the positions to delete to an ArrayList
		for (int i = 0; i < filteredReviews.size(); i++) {
			if (aboveOrBelow.equals("above")) {
				
				if (copy.get(i).getStars() < stars) {
					
					positionsToRemove.add(i);
				}
			}
			else {
						
				if (copy.get(i).getStars() > stars) {
							
					positionsToRemove.add(i);
				}
			}
		}

		// Removes those positions to not disturb the order of this.filteredReviews
			for (int i = positionsToRemove.size() - 1; i >= 0 ; i--) {
				
				copy.remove((int) positionsToRemove.get(i));
			}
			
		return copy;
	}
	
	/**
	 * Removes all Reviews from this.filteredReviews that do not match the
	 * given criteria.
	 * 
	 * List of filters:
	 * - airline
	 * - passenger
	 * - stars
	 * 
	 * @param filters A String that represents the filter to use.
	 */
	private ArrayList<Review> filterReviews() {
		
		System.out.println("\n\nEnter on of the following options to filter by: \n- Airline\n- Passenger\n- Stars\n");

		String filter = scanner.nextLine();
		while (filter.length() < 2) {
			
			System.out.println("Input must be at least two characters...\n\nTry again!\n");

			filter = scanner.nextLine();
		}
		filter = filter.toLowerCase().substring(0, 2);

		ArrayList<Review> filtered = new ArrayList<Review>();
		
		if (filter.equals("ai")) {
			
		// Get Airline's name
			System.out.print("\n\nEnter the full name of the Airline you want to see: ");
			String airline = scanner.nextLine();
			
			filtered = this.filterByAirline(airline);
		}
		else if (filter.equals("pa")) {
			
		// Get Passenger's name
			System.out.print("\n\nEnter the full name of the Passenger you want to see: ");
			String passenger = scanner.nextLine();
			
			filtered = this.filterByPassenger(passenger);
		}
		else if (filter.equals("st")) {
			
		// Get the number of stars they want to see
			String starsMessage = "\n\nEnter the number of stars you want to see: ";
			System.out.print(starsMessage);
			
		// Checks the amount of stars they asked to see
			int stars = -1;
	   	
	   	while(true) {
	   		
	   	// If the input for stars is not an int or is out of the acceptable range, throws an exception
	   	   try {
	   	   	stars = Integer.parseInt(scanner.nextLine().trim());
	   	   	
	   	   	if (stars > 5 || stars < 1) {

						throw new NumberFormatException();
					}
	   	      break;
	   	   }
	   	// If the user does not enter an integer, asks for an integer again
	   	   catch(IllegalArgumentException e) {
	   	   	
	   	   	System.out.println("Stars must be an int between 1 and 5!" + starsMessage);
	   	   }
	   	}
			
			System.out.print("\nDo you want to see above or below this number: ");
			
		// If aboveOrBelow.equals("above"), filter with stars and above, else filter with stars and below
			String aboveOrBelow = "above";
			
			if (!scanner.nextLine().trim().toLowerCase().equals("above")) aboveOrBelow = "below";
			
			filtered = this.filterByStars(stars, aboveOrBelow);
		}

		return filtered;
	}

	/**
	 * Sorts this.filteredReviews by Airline.
	 * @param ascendingOrDescending What method to sort them by, ascending or descending. 
	 * @return A sorted ArrayList<Review>.
	 */
	protected ArrayList<Review> sortByAirline(String ascendingOrDescending) {
		
	// Creates a copy to filter
			ArrayList<Review> copy = new ArrayList<Review>();
			
			for (int i = 0; i < this.filteredReviews.size(); i++) {
				
				copy.add(this.filteredReviews.get(i));
			}
		
	// Sorts the copy by the given method
		if (ascendingOrDescending.equals("ascending")) Collections.sort(copy, (r1, r2) -> r1.getAirlineName().compareTo(r2.getAirlineName()));
		else Collections.sort(copy, (r1, r2) -> r2.getAirlineName().compareTo(r1.getAirlineName()));
		
		return copy;
	}

	/**
	 * Sorts this.filteredReviews by Passenger name.
	 * @param ascendingOrDescending What method to sort them by, ascending or descending. 
	 * @return A sorted ArrayList<Review>.
	 */
	protected ArrayList<Review> sortByPassenger(String ascendingOrDescending) {
		
	// Creates a copy to filter
			ArrayList<Review> copy = new ArrayList<Review>();
			
			for (int i = 0; i < this.filteredReviews.size(); i++) {
				
				copy.add(this.filteredReviews.get(i));
			}
		
	// Sorts the copy by the given method
		if (ascendingOrDescending.equals("ascending")) Collections.sort(copy, (r1, r2) -> r1.getPassenger().getName().compareTo(r2.getPassenger().getName()));
		else Collections.sort(copy, (r1, r2) -> r2.getPassenger().getName().compareTo(r1.getPassenger().getName()));
		
		return copy;
	}

	/**
	 * Sorts this.filteredReviews by Stars.
	 * @param ascendingOrDescending What method to sort them by, ascending or descending. 
	 * @return A sorted ArrayList<Review>.
	 */
	protected ArrayList<Review> sortByStars(String ascendingOrDescending) {
		
	// Creates a copy to filter
			ArrayList<Review> copy = new ArrayList<Review>();
			
			for (int i = 0; i < this.filteredReviews.size(); i++) {
				
				copy.add(this.filteredReviews.get(i));
			}
		
	// Sorts the copy by the given method
		if (ascendingOrDescending.equals("ascending")) Collections.sort(copy, (r1, r2) -> r1.getStars() - r2.getStars());
		else Collections.sort(copy, (r1, r2) -> r2.getStars() - r1.getStars());
		
		return copy;
	}

	/**
	 * Sorts this.filteredReviews by the given method.
	 * @param howToSort How to sort the Reviews.
	 */
	private ArrayList<Review> sortReviews() {
		
		System.out.println("\n\nEnter on of the following options to sort by: \n- Airline\n- Passenger\n- Stars\n");

		String sort = scanner.nextLine();
		while (sort.length() < 2) {
			
			System.out.println("Input must be at least two characters...\n\nTry again!\n");

			sort = scanner.nextLine();
		}
		sort = sort.toLowerCase().substring(0, 2);
		
		
	// Ask if they want to see them in ascending or descending order
		System.out.print("\nWould you like to sort them in ascending or descending order: ");
		String ascendingOrDescending = "ascending";
		if (!scanner.nextLine().trim().toLowerCase().equals("ascending")) ascendingOrDescending = "descending";

		
		ArrayList<Review> sorted = new ArrayList<Review>();
	// Sorts by airline name
		if (sort.equals("ai")) {
			
			sorted = this.sortByAirline(ascendingOrDescending);
		}
	// Sorts by passenger name
		else if (sort.equals("pa")) {
			
			sorted = this.sortByPassenger(ascendingOrDescending);
		}
	// Sorts by number of stars
		else if (sort.equals("st")) {
			
			sorted = this.sortByStars(ascendingOrDescending);
		}

		return sorted;		
	}
	
	/**
	 * Displays the filtered and sorted Reviews.
	 */
	protected String search() {
		
		String results = "";
		
		if (filteredReviews.size() != 0) {
			
			results += "\nReviews:\n\n";
			
			for (int i = 0; i < filteredReviews.size(); i++) {
			
				if (filteredReviews.get(i).toString() != null) {

					results += filteredReviews.get(i).toString() + "\n\n\n";
				}
			}
			
			results += "\n\n";
		}
		else {
			
			results += "No results found! Update and filter again!\n\n\n\n\n";
		}
		
		return results;
	}
	
	/**
	 * Deep copies the latest Reviews from the Database.
	 */
	private void updateReviews() {
		
		this.filteredReviews = database.getAllReviews();
	}
	
	/**
	 * Starts a review menu for the Passenger.
	 * @return If the user wants to log out, returns -1.
	 * 		  If the user wants return to the previous menu, returns 0.
	 */
	public int startMenu() {
		
	// Store a variable used to end the loop
			int kill = 1;
					
			do {
				// Capture the user's pattern choice
				String menuMessage = "Choose one of the following options:\n\nAdd) Add review\nFilter) Filter reveiws"
						+ "\nSort) Sort reviews\nSearch) Search reviews\nUpdate) Update the list of reviews\n"
						+ "Return) Return to previous menu\nLog out) Log out of the system\n";
				System.out.println(menuMessage);
				String input = scanner.nextLine();
				while (input.length() < 2) {
					
					System.out.println("Input must be more than two characters...\n\n");
					System.out.println(menuMessage);
					input = scanner.nextLine();
				}
				input = input.toLowerCase().substring(0, 2);
				
						
				// Determine which option to display or to exit the program
				switch (input) {
				
				
		// Add
			case "ad": 				
				
			// Add the Review
				Review newReview = this.addReview();

				if (newReview == null) {
					
					System.out.print("\nYou have never flown with this Airline before! You can\nonly review Airlines that"
							+ " you have flown with!\n\n\n\n\n");
				}
				else {
				
					// Adds the Review to the database
						ArrayList<Review> reviews = this.database.getAllReviews();
						reviews.add(newReview);
						this.database.setAllReviews(reviews);		
						System.out.print("\nThank you for your review!!!\n\n\n\n\n");

					// Serializes the database
						database.storeObjects();
						
					// Updates filteredReviews
						this.updateReviews();
				}
								break;
							
								
								
			// Filter		
				case "fi": 
					this.filteredReviews = this.filterReviews();
					System.out.println("\nFilter was a success! " + this.filteredReviews.size() + " reviews were found!\n\n\n\n\n");
								break;
								
								
								
			// Sort	
				case "so": 
					this.filteredReviews = this.sortReviews();
					System.out.print("\nThe list has been sorted!\n\n\n\n\n");
				 				break;
				 				
				 				
				 				
			// Search			
				case "se":
					System.out.println("\n\n" + this.search());
								break;
								
								
								
			// Update		
				case "up":
					this.updateReviews();
					System.out.println("\nUpdated!\n\n\n\n\n");
								break;
								
								
								
			// Return
				case "re":
					kill = 0;
								break;
								
								
								
			// Log out
				case "lo":
					kill = -1;
								break;
										
								
								
				// If the user inputs something besides one of the given options do nothing
				default : break;
				}
					
			} while (kill == 1);
			
			return kill;
	}

}
