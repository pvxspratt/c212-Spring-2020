
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AirlineReviewMenu { 
	
	/**
	 * All of the reviews for the current Airline logged in.
	 */
	private ArrayList<Review> airlineReviews;
	
	/**
	 * The current Airline using the system.
	 */
	private Airline currentAirline;

	/**
	 * An instance of the Database
	 */
	private Database database;
	
	/**
	 * The Scanner for this class.
	 */
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Constructs an AirlineREviewMenu for the user to navigate.
	 * @param currentAirline The current Airline using the system.
	 */
	public AirlineReviewMenu(Database database, Airline currentAirline) { 

		this.database = database;
		this.currentAirline = currentAirline;
		this.airlineReviews = database.getAllReviews();
		ArrayList<Review> temporaryReviews = new ArrayList<>();
		
		for (int i = 0; i < database.getAllReviews().size(); i++) {
			
			temporaryReviews.add(database.getAllReviews().get(i));
		}

	// Filters out all reviews not for the currentAirline
		this.airlineReviews = AirlineReviewMenu.filterByThisAirline(currentAirline.getName(), temporaryReviews);
	}

	/**
	 * Only used for testing.
	 * @param reviews An ArrayList<Review>.
	 */
	protected void setAirlineReviews(ArrayList<Review> airlineReviews) {
		
		this.airlineReviews = airlineReviews;
	}
	
	/**
	 * Only used for testing.
	 * @return An ArrayList<Review>.
	 */
	protected ArrayList<Review> getAirlineReviews() {
		
		return this.airlineReviews;
	}

	/**
	 * Filters all of the instance Reviews by the given Airline name.
	 * @param airline The name of the Airline to filter by.
	 * @return The reviews that meet the requirement.
	 */
	protected static ArrayList<Review> filterByThisAirline(String airline, ArrayList<Review> reviews) {
		
	// Creates a copy to filter
		ArrayList<Review> copy = new ArrayList<Review>();
		
		for (int i = 0; i < reviews.size(); i++) {
			
			copy.add(reviews.get(i));
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
			
		for (int i = 0; i < this.airlineReviews.size(); i++) {
				
			copy.add(this.airlineReviews.get(i));
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
				
		for (int i = 0; i < this.airlineReviews.size(); i++) {
					
			copy.add(this.airlineReviews.get(i));
		}
		
		ArrayList<Integer> positionsToRemove = new ArrayList<Integer>();
		// Adds the positions to delete to an ArrayList
		for (int i = 0; i < copy.size(); i++) {
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
	 * Removes all Reviews from this.airlineReviews that do not match the
	 * given criteria.
	 * 
	 * List of filters:
	 * - passenger
	 * - stars
	 * 
	 * @param filters A String that represents the filter to use.
	 */
	private ArrayList<Review> filterReviews() {
		
		System.out.println("\n\nEnter on of the following options to filter by: \n- Passenger\n- Stars\n");

		String filter = scanner.nextLine();
		while (filter.length() < 2) {
			
			System.out.println("Input must be at least two characters...\n\nTry again!\n");

			filter = scanner.nextLine();
		}
		filter = filter.toLowerCase().substring(0, 2);

		ArrayList<Review> filtered = new ArrayList<Review>();
		this.airlineReviews = AirlineReviewMenu.filterByThisAirline(this.currentAirline.getName(), this.airlineReviews);
	
	// Filters by passenger name
		if (filter.equals("pa")) {
			
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
	 * Sorts this.airlineReviews by Passenger name.
	 * @param ascendingOrDescending What method to sort them by, ascending or descending. 
	 * @return A sorted ArrayList<Review>.
	 */
	protected ArrayList<Review> sortByPassenger(String ascendingOrDescending) {
		
	// Creates a copy to filter
			ArrayList<Review> copy = new ArrayList<Review>();
			
			for (int i = 0; i < this.airlineReviews.size(); i++) {
				
				copy.add(this.airlineReviews.get(i));
			}
		
	// Sorts the copy by the given method
		if (ascendingOrDescending.equals("ascending")) Collections.sort(copy, (r1, r2) -> r1.getPassenger().getName().compareTo(r2.getPassenger().getName()));
		else Collections.sort(copy, (r1, r2) -> r2.getPassenger().getName().compareTo(r1.getPassenger().getName()));
		
		return copy;
	}

	/**
	 * Sorts this.airlineReviews by Stars.
	 * @param ascendingOrDescending What method to sort them by, ascending or descending. 
	 * @return A sorted ArrayList<Review>.
	 */
	protected ArrayList<Review> sortByStars(String ascendingOrDescending) {
		
	// Creates a copy to filter
			ArrayList<Review> copy = new ArrayList<Review>();
			
			for (int i = 0; i < this.airlineReviews.size(); i++) {
				
				copy.add(this.airlineReviews.get(i));
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
		
		System.out.println("\n\nEnter on of the following options to sort by: \n- Passenger\n- Stars\n");

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
	// Sorts by passenger name
		if (sort.equals("pa")) {
			
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
		
		if (this.airlineReviews.size() != 0) {
			
			results += "\nReviews:\n\n";
			
			for (int i = 0; i < this.airlineReviews.size(); i++) {
			
				results += this.airlineReviews.get(i).toString() + "\n\n\n";
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
	 * Only adds reviews that are for the current Airline.
	 */
	private void updateReviews() {
		
	// Updates the stored reviews and filters out all reviews not for the currentAirline
		this.airlineReviews = database.getAllReviews();
		this.airlineReviews = AirlineReviewMenu.filterByThisAirline(this.currentAirline.getName(), this.airlineReviews);;
	}
	
	/**
	 * Starts the menu for the Airline.
	 */
	public int startMenu() {
		
	// Store a variable used to end the loop
		int kill = 1;
						
		do {
		// Capture the user's pattern choice
			String menuMessage = "Choose one of the following options:\n\nFilter) Filter reveiws"
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

			
			
		// Filter		
			case "fi": 
				this.airlineReviews = this.filterReviews();
				System.out.println("\nFilter was a success! " + this.airlineReviews.size() + " reviews were found!\n\n\n\n\n");
						break;
									
									
									
		// Sort	
			case "so": 
				this.airlineReviews = this.sortReviews();
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
