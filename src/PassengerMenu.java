import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A menu class that allows Passengers to search for flights, view their booking
 * history, book new flights, cancel unflown flights, and leave reviews.
 */
@SuppressWarnings("serial")
public class PassengerMenu implements java.io.Serializable {
	
	/**
	 * A filtered list of flights to display to the current Passenger.
	 */
	private ArrayList<Flight> filteredFlights;
	
	/**
	 * how the flights were currently being sorted
	 */
	private String sort;
	
	/**
	 * filters for all the flights
	 */
	private Set<String> filters;
	
	/**
	 * The Passenger that is currently using the system.
	 */
	private Passenger currentPassenger;
	
	/**
	 * A list of all airlines used to grab flights
	 */
	private ArrayList<Airline> airlines;
	
	/**
	 * Used to keep data even if the program is exited and reopened
	 */
	private Database database;
	
	/**
	 * Scanner that is used throughout the class
	 */
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * Creates a menu for the Passenger to use once they have logged in.
	 * @param allFlights All of the flights in the Database.
	 * @param currentPassenger The Passenger object who is currently modifying the system.
	 */
	public PassengerMenu(Database database, Passenger currentPassenger) {
		super();
		
		this.database = database;
		this.filteredFlights = database.getAllFlights();
		this.airlines = database.getAllAirlines();
		this.currentPassenger = currentPassenger;
	}
	
	/**
	 * Removes all flights of airlines that blacklist currentPassenger
	 */
	public void removeBlacklistAirlines() {
		
		this.airlines = database.getAllAirlines();
		for(int i=0; i < airlines.size(); i++){
			if(airlines.get(i).getBlackList().contains(currentPassenger)) {
				for(int j=0; j < filteredFlights.size(); j++) {
					if(filteredFlights.get(j).getAirline().equals(airlines.get(i))) {
						filteredFlights.remove(j);
						j--;
					}
				}
			}
		}
	}

	/**
	 * Removes all flights from this.filteredFlights that do not match the
	 * given criteria.
	 * @param filters A Set<String> that represent filters.
	 */
	void filterFlights(Set<String> filters) {
		for(int i = 0; i < filteredFlights.size(); i++) {
			Flight currentFlight = filteredFlights.get(i);
			if(filters.contains("Airline:" + currentFlight.getAirline().getName().toLowerCase()) || filters.contains("Source:" + currentFlight.getSource().toLowerCase()) 
					|| filters.contains("Destination:" + currentFlight.getDestination().toLowerCase()) ||  filters.contains("Layover:" + currentFlight.getNumberOfLayovers())) {
				
			} else {
				filteredFlights.remove(i);
				i--;
			}
		}
	}

	/**
	 * Sorts this.filteredFlights by the given method.
	 * @param howToSort How to sort the flights.
	 * Can be sorted by layovers, airline, source, destination, 
	 */
	ArrayList<Flight> sortFlights(String howToSort) {
		ArrayList<Flight> sortedFlights = new ArrayList<Flight>();
		for(int i=0; i < filteredFlights.size(); i++) {
			sortedFlights.add(filteredFlights.get(i));
		}
		if(howToSort.equals("layovers")) {
			Collections.sort(sortedFlights, (r1, r2) -> ((Integer)(r1.getNumberOfLayovers())).compareTo((Integer)(r2.getNumberOfLayovers())));
		} else if (howToSort.equals("airline")){
			Collections.sort(sortedFlights, (r1, r2) -> r1.getAirline().getName().compareTo(r2.getAirline().getName()));
		} else if (howToSort.equals("source")) {
			Collections.sort(sortedFlights, (r1, r2) -> r1.getSource().compareTo(r2.getSource()));
		} else if (howToSort.equals("destination")) {
			Collections.sort(sortedFlights, (r1, r2) -> r1.getDestination().compareTo(r2.getDestination()));
		} else if (howToSort.equals("time")) {
			Collections.sort(sortedFlights, (r1, r2) -> ((Long)r1.getDepartureTime()).compareTo(r2.getDepartureTime()));
		}
		return sortedFlights;
	}

	/**
	 * Calculates the cost of the Flight the Passenger is trying to book
	 * @param flight The Flight the user wants to book.
	 * @param classSeat The class the user wants to fly in.
	 * @param bags The amount of bags the user wants to bring on the flight.
	 * @return The cost of the Flight.
	 */
	int calculateFlightCost(Flight flight, String classSeat, int bags) {
		int cost = 0;
		if(classSeat.equals("economy")) {
			cost += 100;
		} else if (classSeat.equals("premiumEconomy")) {
			cost += 156;
		} else if (classSeat.equals("business")) {
			cost += 288;
		} else if (classSeat.equals("firstClass")) {
			cost += 947;
		}
		
		if(bags <= 0) {
			cost += 0;
		} else if(bags == 1) {
			cost += 30;
		} else if(bags == 2) {
			cost += 70;
		} else {
			cost += 70 + ((bags-2)*150);
		}
		
		cost += flight.getMiles();
		
		
		return cost;
	}
	
	/**
	 * @param cost - the cost of the flight + bags + seat
	 * @param classSeat - the Class of seat the customer is buying
	 * @param flight - the Flight the ticket correlates with
	 * @param airline - Airline the ticket is with
	 * @param time - time the ticket was bought
	 * @return Ticket to be in passengers current Tickets
	 */
	private Ticket makeTicket(int cost, String classSeat, Flight flight, Airline airline, long time) {
		return new Ticket(cost, classSeat, flight, airline, time);
	}
	/**
	 * @param mealPref - mealPreference for flight
	 * @return Ticket to be in passengers current Tickets
	 */
	private Ticket makeTicket(int cost, String classSeat, Flight flight, Airline airline, long time, String mealPref){
		return new Ticket(cost, classSeat, flight, airline, time, mealPref);
	}

	/**
	 * Provides the Passenger their Ticket for the Flight after displaying the
	 * cost of the flight.
	 * @param Flight f - provides 
	 */
	private void bookFlight(Flight f) {

		System.out.println("Enter in how many bags you're bringing for the flight.");
		int bags = scanner.nextInt();
		System.out.println("Enter in a class: \n 'economy' for economy  \n 'premiumeconomy' for Premium Economy \n 'business' for business \n 'firstclass' for First Class");
		String classSeat = scanner.nextLine().toLowerCase();
		boolean costCalculated = true;
		while(costCalculated) {
			if(classSeat.equals("economy") && f.getEconomyClassSeats() > 0) {
				f.setEconomyClassSeats(f.getEconomyClassSeats()-1);
				System.out.println("The cost of the flight is " + this.calculateFlightCost(f, classSeat, bags));
				costCalculated = false;
			} else if(classSeat.equals("premiumeconomy") && f.getPremiumEconomyClassSeats() > 0) {
				f.setPremiumEconomyClassSeats(f.getEconomyClassSeats()-1);
				System.out.println("The cost of the flight is " + this.calculateFlightCost(f, classSeat, bags));
				costCalculated = false;
			} else if(classSeat.equals("business") && f.getBusinessClassSeats() > 0) {
				f.setBusinessClassSeats(f.getBusinessClassSeats()-1);
				System.out.println("The cost of the flight is " + this.calculateFlightCost(f, classSeat, bags));
				costCalculated = false;
			} else if(classSeat.equals("firstclass") && f.getFirstClassSeats() > 0) {
				f.setFirstClassSeats(f.getFirstClassSeats()-1);
				System.out.println("The cost of the flight is " + this.calculateFlightCost(f, classSeat, bags));
				costCalculated = false;
			} else {
				System.out.println("The class you specified was not identified, or there are no seats of that class available on that flight.");
				System.out.println("Enter a new class with open seats or make sure that you type your class correctly:");
				classSeat = scanner.nextLine().toLowerCase();
			}
		}
		int cost = calculateFlightCost(f, classSeat, bags);
		System.out.println("Would you like to pay with Cash, Credit, or Miles? \n Type: 'cash' for cash 'cred' for credit or 'miles' for miles"); 
		String payment = scanner.nextLine();
		boolean ticketNotPaid = true;
		while(ticketNotPaid) {
			if(payment.equals("miles")) {
				if(currentPassenger.getNumberOfMiles() >= f.getMiles()) {
					currentPassenger.setNumberOfMiles(currentPassenger.getNumberOfMiles() - f.getMiles());
					ticketNotPaid = false;
				} else  {
					System.out.println("You don't have enough miles would you like to pay, with partly miles and credit/cash? \n 'y' for yes & 'n' for no.");
					String milesYesOrNo = scanner.nextLine();
					if(milesYesOrNo.equals("y")) {
						currentPassenger.setNumberOfMiles(Math.abs(currentPassenger.getNumberOfMiles() - f.getMiles()));
						ticketNotPaid = false;
					} else {
						ticketNotPaid = false;
					}
				}
			}  else if(payment.equals("cash") || (payment.equals("cred"))) {
				ticketNotPaid = false;
			} else {
				System.out.println("The input must be 'cash', 'cred', or 'miles'");
				payment = scanner.nextLine();
			}
		}
		
		if(1500 < f.getMiles()) {
			String food;
			System.out.println("What meal would you like for your trip?\n BW) Bucket of Worms \n P) Bucket of Peanuts \n "
					+ "PR) Bucket of Pretzels \n If nothing is put you get pretzels as default");
			food = scanner.nextLine().toLowerCase();
			if(food.equals("bw")) {
				currentPassenger.getCurrentTickets().add(makeTicket(cost, classSeat, f, f.getAirline(), System.currentTimeMillis(), "Bucket of Worms"));
			} else if(food.equals("p")) {
				currentPassenger.getCurrentTickets().add(makeTicket(cost, classSeat, f, f.getAirline(), System.currentTimeMillis(), "Bucket of Peanuts"));
			} else {
				currentPassenger.getCurrentTickets().add(makeTicket(cost, classSeat, f, f.getAirline(), System.currentTimeMillis(), "Bucket of Pretzels"));
			}
		} else {
			currentPassenger.getCurrentTickets().add(makeTicket(cost, classSeat, f, f.getAirline(), System.currentTimeMillis()));
		}
	}
	
	/**
	 * Checks tickets to see if flight in ticket has already departed and moves to old tickets
	 */
	private void checkOutOfDateTickets() {
		for(int i=0; i < currentPassenger.getCurrentTickets().size(); i++) {
			if(currentPassenger.getCurrentTickets().get(i).getFlight().getDepartureTime() < System.currentTimeMillis()) {
				currentPassenger.getOldTickets().add(currentPassenger.getCurrentTickets().get(i));
				currentPassenger.getCurrentTickets().remove(i);
			}
		}
	}
	
	/**
	 * Sees if a flight is completely booked and removes it from the filteredFlights
	 */
	private void checkCompletelyBooked() {
		for(int i=0; i < filteredFlights.size(); i++) {
			if(filteredFlights.get(i).getBusinessClassSeats()==0 && filteredFlights.get(i).getEconomyClassSeats()==0 
					&& filteredFlights.get(i).getPremiumEconomyClassSeats()==0 && filteredFlights.get(i).getFirstClassSeats()==0) {
				filteredFlights.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Check if a Flight has already departed
	 */
	private void checkFlightHasLeft(){
		for(int i=0; i < filteredFlights.size(); i++) {
			if(filteredFlights.get(i).getDepartureTime() < System.currentTimeMillis()) {
				filteredFlights.remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes a Ticket from the Passenger's possession
	 * @param ticketToRemove - Ticket object that will be removed from the passengers current flights
	 */
	private void cancelFlight(Ticket ticketToRemove) {
		for(int i=0; i < currentPassenger.getCurrentTickets().size(); i++) {
			if(currentPassenger.getCurrentTickets().get(i).equals(ticketToRemove)) {
				String seatClass = currentPassenger.getCurrentTickets().get(i).getTicketType();
				Flight f = currentPassenger.getCurrentTickets().get(i).getFlight();
				if(seatClass.equals("economy")) {
					f.setEconomyClassSeats(f.getEconomyClassSeats()+1);;
				} else if(seatClass.equals("premiumeconomy")) {
					f.setPremiumEconomyClassSeats(f.getPremiumEconomyClassSeats()+1);
				} else if(seatClass.equals("firstclass")) {
					f.setFirstClassSeats(f.getFirstClassSeats()+1);
				} else if(seatClass.equals("business")) {
					f.setBusinessClassSeats(f.getBusinessClassSeats()+1);
				}
				currentPassenger.getCurrentTickets().remove(i);
			}
		}
	}
	
	/**
	 * 
	 * @return the filters to put on the arraylist of filteredFlights
	 */
	public Set<String> getFilters() {
		return filters;
	}

	/**
	 * setFilters - sets filters to new filters
	 * @param filters - is new set of filters
	 */
	public void setFilters(Set<String> filters) {
		this.filters = filters;
	}
	/**
	 * 
	 * @return the arraylist of filteredFlights
	 */
	ArrayList<Flight> getFilteredFlights(){
		return filteredFlights;
	}

	/**
	 * Starts a main menu for the Passenger.
	 * 
	 */
	public void startMenu() {
		int kill = 0;
		do {
			filteredFlights.clear();
			for(int k = 0; k < airlines.size(); k++) {
				for(int l = 0; l < airlines.get(k).getAllFlights().size(); l++) {
					filteredFlights.add(airlines.get(k).getAllFlights().get(l));
				}
			}
			database.setAllFlights(filteredFlights);
			
			
			if(sort != null) {
				filteredFlights = this.sortFlights(sort);
			}
			
			this.removeBlacklistAirlines();
			if(filters != null) {
				this.filterFlights(filters);	
			}
			
			this.checkOutOfDateTickets();
			this.checkCompletelyBooked();
			this.checkFlightHasLeft();
			
			System.out.println("All flights: ");
			for(int i = 0; i < filteredFlights.size(); i++) {
				System.out.println((i+1) + ")" + filteredFlights.get(i).toAirlineString());
			}
			System.out.println("Choose one of the following options:\n\n Book) Book an upcoming flight \n Check) Check your upcoming flights\n Old) Check your old flights\n"
					+ " Sort) Sorts available flights\n Filter) Filter available flights\n Review) Open Review Menu\n Logout) Logout of app");
			String input = scanner.nextLine().trim();
			if(input.length() < 3) {
				System.out.println("The word inputted must be one of the words before the option");
				System.out.println("Choose one of the following options:\n\n Book) Book an upcoming flight \n Check) Check your upcoming flights\n Old) Check your old flights\n"
						+ " Sort) Sorts available flights\n Filter) Filter available flights\n Review) Open Review Menu\n Logout) Logout of app");
				input = scanner.nextLine().trim();
			}
			
			input = input.toLowerCase().trim().substring(0, 2);
			
			switch(input) {
				case "bo":
					System.out.println("Choose the number of the flight you would like to take: \n Enter a 0 or any negative number if you would like to not book a flight");
					int flightIndex = scanner.nextInt();
					if(flightIndex <= 0) {
						break;
					} else {
						bookFlight(filteredFlights.get(flightIndex-1));
					}
					break;
				case "ch":
					System.out.println("All of your upcoming flights: ");
					for(int i=0; i < currentPassenger.getCurrentTickets().size(); i++) {
						System.out.println((i+1) + ")" + currentPassenger.getCurrentTickets().get(i).toString());
					}
					System.out.println("Would you like to remove any of the flights? \n 'y' for yes and 'n' for no' ");
					String yesOrNo = scanner.nextLine();
					while(true) {
						if(yesOrNo.equals("y")) {
							System.out.println("Enter the number that is infront of the ticket that you would like to remove: ");
							System.out.println("If don't want to remove a ticket, type: 'rem");
							String ticketRemove = scanner.nextLine();
							if(ticketRemove.equals("rem")) {
								break;
							} else {
								cancelFlight(currentPassenger.getCurrentTickets().get(Integer.parseInt(ticketRemove)-1));
								System.out.println("Would you like to cancel more flights? \n 'y' for yes and 'n' for no");
								yesOrNo = scanner.nextLine();
							}
							
						} else if(yesOrNo.equals("n")) {
							break;
						} else {
							System.out.println("Enter a value: 'y' for yes and 'n' for no ");
							yesOrNo = scanner.nextLine();
						}
					}
					break;
				case "fi":

					System.out.println("Filter options:\n- Airline\n- Source\n- Destination\n- Layovers\n");
					String filterChoice = scanner.nextLine().toLowerCase().trim();

					Set<String> filters = new HashSet<String>();
					if (filterChoice.equals("airline")) {
						
						System.out.println("Enter all of the airline names you would like to fly on: \n Enter 'e' to be done with filtering airlines.");
						String airline = scanner.nextLine().toLowerCase().trim();
						boolean airlineTest = true;
						while(airlineTest) {
							if(airline.equals("e")) {
								airlineTest = false;
							} else {
								filters.add("Airline:" + airline);
								airline = scanner.nextLine().toLowerCase().trim();
							}
						}
					}
					else if (filterChoice.equals("source")) {

						System.out.println("Enter all of the sources you would like to fly from: \n Enter 'e' to be done with filtering sources.");
						String source = scanner.nextLine().toLowerCase().trim();
						boolean sourceTest = true;
						while(sourceTest) {
							if(source.equals("e")) {
								sourceTest = false;
							} else {
								filters.add("Source:" + source);
								source = scanner.nextLine().toLowerCase().trim();
							}
						}
					}
					else if (filterChoice.equals("destination")) {

						System.out.println("Enter all of the destinations you would like to fly to: \n Enter 'e' to be done with filtering destinations.");
						String destination = scanner.nextLine().toLowerCase().trim();
						boolean destinationTest = true;
						while(destinationTest) {
							if(destination.equals("e")) {
								destinationTest = false;
							} else {
								filters.add("Destination:" + destination);
								destination = scanner.nextLine().toLowerCase().trim();
							}
						}
					}
					else if (filterChoice.equals("layovers")) {

						System.out.println("Enter amount of layovers you would like to have: \n Enter 'e' to be done with filtering layovers.");
						String layover = scanner.nextLine().toLowerCase().trim();
						boolean layoverTest = true;
						while(layoverTest) {
							if(layover.equals("e")) {
								layoverTest = false;
							} else {
								filters.add("Layover:" + layover);
								layover = scanner.nextLine().toLowerCase().trim();
							}
						}
					}
					
					this.setFilters(filters);
					
					break;
					
				case "lo":
					kill = -1;
					break;
					
				case "ol":
					System.out.println("Old tickets: ");
					for(int i=0; i < currentPassenger.getOldTickets().size(); i++) {
						System.out.println(currentPassenger.getOldTickets().get(i).toString());
					}
					
					System.out.println("Press 'e' to exit old tickets:");
					String exitStr = scanner.nextLine();
					if(exitStr.equals("e")) {
						break;
					}  else { 
						System.out.println("Enter 'e' to exit");
						exitStr = scanner.nextLine();
					}
					break;
				case "so":
					System.out.println("To Sort:\ntype 'layovers' to sort in increasing order based on layovers\n"
							+ "type 'airline' to sort alphabetically by the airline's name\n"
							+ "type 'source' to sort alphabetically by the name of the source\n"
							+ "type 'destination' to sort alphabetically by the name of the destination"
							+ "type 'time' to sort in increasing order based on the time of departure");
					String sort = scanner.nextLine();
					this.sort = sort;
					break;
				case "re":
					System.out.print("\n\n\n\n\nPassenger Review Menu\n===================\n");
				   	PassengerReviewMenu prm = new PassengerReviewMenu(this.database, this.currentPassenger);
				    kill = prm.startMenu();
				   	
					break;
					
			}
			
		} while (kill != -1);
		
	}
}

