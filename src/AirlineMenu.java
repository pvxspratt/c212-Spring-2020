import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AirlineMenu {
	/**
	 * The Airline for this specific AirlineMenu.
	 */
	private Airline currentAirline;

	/**
	 * The Database to be used throughout the program.
	 */
	private Database database;

	private Scanner in = new Scanner(System.in);

	/**
	 	Constructs the AirlineMenu.
	 */
	public AirlineMenu(Database database, Airline currentAirline) {

		this.database = database;
		this.currentAirline = currentAirline;
	}

	/**
	 * Creates a Flight from user's String, Integer, and Boolean input.
	 * @return a new Flight
	 */
	private Flight createFlight() {

		Random random = new Random();

		// currentAirline
		System.out.println("Current airline: " + currentAirline.getName());

		// flightNumber
		int flightNumber = random.nextInt(1000000);
		flightNumber = currentAirline.flightExists(flightNumber);

		// Flight source
		String departingCity = "";
		System.out.print("Enter the city in which flight will depart: ");
		if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
			departingCity = in.nextLine();
		} else {
			System.out.print("   That was not a String input, input a City as a String ");
			in.nextLine();
			if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
				departingCity = in.nextLine();
			} else {
				System.out.print("   Nope. Do it again fool ");
				in.nextLine();
				if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
					departingCity = in.nextLine();
				} else {
					System.out.print("   You'll be departing from Chicago because you're dumb\n");
					departingCity = "Chicago";
				}
			}
		}

		// Flight destination
		String landingCity;
		System.out.print("Enter the city at which flight will arrive upon landing: ");
		if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
			landingCity = in.nextLine();
		} else {
			System.out.print("   That wasn't a String input, a City is a String ");
			in.nextLine();
			if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
				landingCity = in.nextLine();
			} else {
				System.out.print("   Nope. again ");
				in.nextLine();
				if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
					landingCity = in.nextLine();
				} else {
					System.out.print("   Dumb airlines go to Rome\n");
					landingCity = "Rome";
				}
			}
		}

		// Flight miles
		String milesMessage = "Please enter the distance of the flight in miles: ";
		System.out.print(milesMessage);

		int miles = -1;

		while(true) {

			// If the input for stars is not an int or is out of the acceptable range, throws an exception
			try {
				miles = Integer.parseInt(in.nextLine().trim());

				if (miles < 0) {

					throw new IllegalArgumentException();
				}
				break;
			}
			// If the user does not enter an integer, asks for an integer again
			catch(IllegalArgumentException e) {

				System.out.println("\nYou must enter a positive number!\n" + milesMessage);
			}
		}

		// Flight departureTime
		long departingTime;
		System.out.print("Enter number of minutes before the flight is scheduled to take off: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			departingTime = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   Enter time as an Integer ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				departingTime = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   Integer means numbers ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					departingTime = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   Stupid airlines take of at 0450\n");
					departingTime = 5;
				}
			}
		}
		departingTime = System.currentTimeMillis() + (departingTime * 60000);

		// Flight numberOfLayovers
		int layovers;
		System.out.print("Enter amount of layovers: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			layovers = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   Enter an Integer ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				layovers = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   Numbers include numbers 0-9 ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					layovers = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   You're an idiot, but I can be nice\n");
					layovers = 0;
				}
			}
		}

		// Flight roundOrOne
		boolean roundOrOne;
		System.out.print("True or false. This flight will be a round trip: ");
		if(in.hasNextBoolean() && !in.hasNextInt()) {
			if(Boolean.parseBoolean(in.nextLine())) {
				//System.out.print("This flight will be a round trip");
				roundOrOne = true;
			} else {
				//System.out.print("This is a one way flight");
				roundOrOne = false;
			}
		} else {
			System.out.print("   Vrai ou faux. Ce vol sera un aller-retour: ");
			in.nextLine();
			if(in.hasNextBoolean() && !in.hasNextInt()) {
				if(Boolean.parseBoolean(in.nextLine())) {
					//System.out.println("C'est un aller-retour");
					roundOrOne = true;
				} else {
					//System.out.println("C'est un vol aller");
					roundOrOne = false;
				}
			} else {
				System.out.print("   True is a round trip. False is a one-way flight");
				in.nextLine();
				if(in.hasNextBoolean() && !in.hasNextInt()) {
					if(Boolean.parseBoolean(in.nextLine())) {
						//System.out.println("Round");
						roundOrOne = true;
					} else {
						//System.out.println("C'est un vol aller");
						roundOrOne = false;
					}
				} else {
					System.out.print("   You know what, don't come back\n");
					roundOrOne = false;
				}
			}
		}

		// Flight dates
		String date;
		System.out.print("Enter date for the flight with no spaces: ");
		if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
			date = in.nextLine();
		} else {
			System.out.print("   That wasn't a String input, enter a date like this: April26,2016 ");
			in.nextLine();
			if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
				date = in.nextLine();
			} else {
				System.out.print("   What was that? ");
				in.nextLine();
				if(in.hasNext() && !in.hasNextInt() && !in.hasNextBoolean()) {
					date = in.nextLine();
				} else {
					System.out.print("   Your flight will be on a fun day\n");
					date = "December21,2022";
				}
			}
		}

		// Flight firstClassSeats
		int probableFerrariOwners;
		System.out.print("How many first class seats are on this flight?: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			probableFerrariOwners = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   Enter a number ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				probableFerrariOwners = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   I said a number ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					probableFerrariOwners = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   People should aspire to be like these people\n");
					probableFerrariOwners = 8;
				}
			}
		}

		// Flight businessClassSeats
		int probableBeemerOwners;
		System.out.print("How many business class seats are on this flight?: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			probableBeemerOwners = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   No, a number ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				probableBeemerOwners = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   Huh? ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					probableBeemerOwners = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   We like bmw\n");
					probableBeemerOwners = 80;
				}
			}
		}

		// Flight premiumEconomyClassSeats
		int probableAudiOwners;
		System.out.print("How many premium economy class seats?: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			probableAudiOwners = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   I need a number ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				probableAudiOwners = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   Some people have thick skulls ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					probableAudiOwners = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   They think there're special with their Audi/Volvo/Lexus \n   but they're actually just a pain in the a-\n");
					probableAudiOwners = 32;
				}
			}
		}

		// Flight economyClassSeats
		int probableNissanOwners;
		System.out.print("How many economy class seats?: ");
		if(in.hasNextInt() && !in.hasNextBoolean()) {
			probableNissanOwners = Integer.parseInt(in.nextLine());
		} else {
			System.out.print("   Digits please ");
			in.nextLine();
			if(in.hasNextInt() && !in.hasNextBoolean()) {
				probableNissanOwners = Integer.parseInt(in.nextLine());
			} else {
				System.out.print("   Is a cat typing this? ");
				in.nextLine();
				if(in.hasNextInt() && !in.hasNextBoolean()) {
					probableNissanOwners = Integer.parseInt(in.nextLine());
				} else {
					System.out.print("   so, how does it feel to be Poor?\n");
					probableNissanOwners = 244;
				}
			}
		}

		// Flight totalSeats
		int totalSeats = probableFerrariOwners + probableBeemerOwners + probableAudiOwners + probableNissanOwners;

		Flight flight = new Flight(currentAirline, flightNumber, departingCity, landingCity, miles, departingTime, 
				layovers, roundOrOne, date, totalSeats, probableFerrariOwners, probableBeemerOwners, 
				probableAudiOwners, probableNissanOwners);

		return flight;
	}

	/**
	 * Used for testing only
	 */
	protected Flight makeFlight(Airline currentAirline, int flightNumber, String departingCity, String landingCity, int miles, long departingTime, 
			int layovers, boolean roundOrOne, String date, int totalSeats, int probableFerrariOwners, int probableBeemerOwners, 
			int probableAudiOwners, int probableNissanOwners) {
		return new Flight(currentAirline, flightNumber, departingCity, landingCity, miles, departingTime, 
				layovers, roundOrOne, date, totalSeats, probableFerrariOwners, probableBeemerOwners, 
				probableAudiOwners, probableNissanOwners);
	}

	/**
	 * Adds a flight to the Airline's allFlights list.
	 * @param flight the flight to add
	 */
	private void addFlight(Flight flight) {
		ArrayList<Flight> allFlights = currentAirline.getAllFlights();
		allFlights.add(flight);
		this.currentAirline.setAllFlights(allFlights);
	}

	/**
	 * Blacklists a passenger to prevent them from booking any flights of this Airline in the future.
	 * @param p the passenger to be blacklisted
	 */
	private void blackListPassenger(Passenger p) {

		ArrayList<Passenger> blackList = currentAirline.getBlackList();
		blackList.add(p);
	}

	/**
	 * Starts the AirlineMenu.
	 */
	public void startMenu() {
		int exit = 0;

		do {

			ArrayList<Airline> allAirlines = database.getAllAirlines();
			for (int i = 0; i < allAirlines.size(); i++) {

				if (allAirlines.get(i).getName().toLowerCase().trim().equals(this.currentAirline.getName().toLowerCase().trim())) {

					allAirlines.set(i, this.currentAirline);
				}
			}
			database.setAllAirlines(allAirlines);
			database.storeObjects();

			System.out.println();
			String menuMessage = "To view flights, enter 'view'"
					+ "\nTo remove a flight, enter 'remove'      "
					+ "\nTo blacklist a passenger, enter 'blacklist'      "
					+ "\nTo add a flight, enter 'add'      "
					+ "\nTo enter airline review menu, enter 'ar'"
					+ "\nTo leave menu, enter 'exit'";
			System.out.println(menuMessage);
			String input = in.nextLine();
			while (input.length() < 2) {

				System.out.println("Input must be more than two characters...\n\n");
				System.out.println(menuMessage);
				input = in.nextLine();
			}
			input = input.toLowerCase().substring(0, 2);

			switch(input) {

			case "vi" : System.out.println();
			ArrayList<Flight> allFlights = currentAirline.getAllFlights();
			ArrayList<String> all = currentAirline.getAllFlightsAsStrings(allFlights);
			System.out.println(all);
			break;


			case "ad" : System.out.print("");
			Flight newFlight = createFlight();
			System.out.print("This is flight: \n");

			System.out.print(newFlight.toAirlineString());

			addFlight(newFlight);
			System.out.print("Flight added.");
			break;
			case "re" : System.out.println("Remove one of the following flights");
			ArrayList<Flight> plannedFlights = currentAirline.getAllFlights();
			ArrayList<Integer> plannedFlightsStrings = currentAirline.getStringFlightList(plannedFlights);
			System.out.println(plannedFlightsStrings);

			System.out.println("Enter the flight name of the fight that is being removed exactly as it is show above");	
			Integer flightToRemove = Integer.parseInt(in.nextLine());

			if(plannedFlightsStrings.contains(flightToRemove)) {
				Flight removedFlight = currentAirline.getCorrespondingFlight(flightToRemove, plannedFlights);
				plannedFlights.remove(removedFlight);

				ArrayList<Passenger> passengers = removedFlight.getPassengers();
				for(Passenger p : passengers) {
					p.setNumberOfMiles(p.getNumberOfMiles() + removedFlight.getMiles());
				}
			} else {
				System.out.println("That flight does not exist");
			}	
			break;
			case "bl" : System.out.println("Please enter the username of the passenger you want to blacklist:");
			String passengerUsername = in.nextLine();
			Passenger passenger = null;

			ArrayList<Passenger> allPassengers = database.getAllPassengers();
			for (int i = 0; i < allPassengers.size(); i++) {

				if (allPassengers.get(i).getUsername().toLowerCase().trim().equals(passengerUsername.toLowerCase().trim())) {

					passenger = allPassengers.get(i);
				}
			}

			if (passenger == null) {
				System.out.println("The username you entered was not found...");
			}
			else {
				this.blackListPassenger(passenger);
			}

			break;
			case "ex" : System.out.println("Exiting menu... bye");
			exit = 1;
			break;
			case "ar": 
				System.out.print("\n\n\n\n\nAirline Review Menu\n==================\n");
				AirlineReviewMenu arm = new AirlineReviewMenu(this.database, this.currentAirline);
				int output = arm.startMenu();
				if(output == -1) exit = 1;
				break;

			default : break;
			}

			database.storeObjects();
		} while(exit != 1);

	} 

}
