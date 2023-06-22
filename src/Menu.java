import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Dylan Herthoge
 *	This class is called at the beginning of the program.
 *	It uses a menu to guide users through the system, asking for input
 * to to register as a Passenger, Airline, log in/out, and shutdown the system.
 */
public class Menu {
	
	/**
	 * The Database to be used throughout the program.
	 */
	private Database database;
	
	/**
	 * The Scanner for this class.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Constructs a Menu to navigate through the system.
	 */
	public Menu(Database database) {
		
		super();
		
		this.database = database;
	}

	/**
	 * Adds a new Passenger to the Database. 
	 * @param name The First and Last name of the Passenger separated by a space.
	 * @param username The chosen username of the Passenger.
	 * @param password The chosen password of the Passenger.
	 * @param passwordCheck The chosen password of the Passenger again. Verifies the Passenger
	 * 							entered the password they thought they did.
	 */
	private Passenger registerNewPassenger() {
		
	// Asks the Passenger for their name
		System.out.print("\n\nPlease enter your name: ");
		String name = scanner.nextLine().trim();
		
	// Gets the current ArrayList<Passenger>
		ArrayList<Passenger> allPassengers = database.getAllPassengers();
		
	// Asks for username
		String usernameMessage = "\nPlease enter your desired username: ";
		System.out.print(usernameMessage);
      String username = "";
   	
   // Checks to make sure the username the Passenger wants is not taken
   	while(true) {
   		
   	   try {
   	   	
   	   	username = scanner.nextLine().trim();
   	   	
   	   	for (int i = 0; i < allPassengers.size(); i++) {
   			
   	   	// If it is taken, throws and exception
   				if (username.toLowerCase().trim().equals(allPassengers.get(i).getUsername().toLowerCase().trim())) {
   					
   					throw new DuplicateUsernameException();
   				}
   			}
   	      break;
   	   }
   	// If an exception is throw, asks the user to re-enter their username
   	   catch(DuplicateUsernameException e) {
   	   	
   	   	System.out.print("\nThis username is already taken!\n" + usernameMessage);
   	   }
   	}
		
		
		
		String passwordMessage = "\nPlease enter your desired password: ";
		String passwordCheckMessage = "\nPlease re-enter your desired password: ";
		
      String password = "";
      String passwordCheck = "";
   	
   	while(true) {
   		
   	// Checks to make sure the passwords they entered match
   	   try {
   	   	

   			System.out.print(passwordMessage);
   			password = scanner.nextLine().trim();
   			
   			System.out.print(passwordCheckMessage);
   			passwordCheck = scanner.nextLine().trim();
   	   	
   	   	if (!password.equals(passwordCheck)) {
					
   	   		throw new DifferentPasswordException();
				}
   	      break;
   	   }
   	// If an exception is throw, ask for passwords again
   	   catch(DifferentPasswordException e) {
   	   	
   	   	System.out.print("The passwords you entered don't match!\n");
   	   }
   	}
   	
		return new Passenger(name, username, password, 0, new ArrayList<Ticket>(), new ArrayList<Ticket>(),
				new PassengerFlightNeeds("", null, null, 0, 0, ""));
	}

	/**
	 * Adds a new Airline to the Database.
	 * @param name The name of the Airline.
	 * @param username The chosen username of the Airline.
	 * @param password The chosen password of the Airline.
	 * @param passwordCheck The chosen password of the Airline again. Verifies the Airline
	 * 							entered the password they thought they did.
	 */
	private Airline registerNewAirline() {
		
	// Asks the Airline for it's name
		System.out.print("\n\nPlease enter the name of you Airline: ");
		String name = scanner.nextLine().trim();
			
	// Gets the current ArrayList<Airline>
		ArrayList<Airline> allAirlines = database.getAllAirlines();
			
	// Asks for username
		String usernameMessage = "\nPlease enter your Airline's desired username: ";
		System.out.print(usernameMessage);
	   String username = "";
	   	
	// Checks to make sure the Airline hasn't registered before
	  	while(true) {
	   		
	  	   try {
	   	   	
	  	   	username = scanner.nextLine().trim();
	   	   	
	  	   	for (int i = 0; i < allAirlines.size(); i++) {
	   			
	  	   	// If it is taken, throws and exception
	  				if (username.toLowerCase().trim().equals(allAirlines.get(i).getUsername().toLowerCase().trim())) {
	  					
	  					throw new DuplicateUsernameException();
	  				}
	  			}
	  	      break;
	  	   }
	  	// If an exception is throw, asks the user to re-enter their username
	  	   catch(DuplicateUsernameException e) {
	   	   	
	  	   	System.out.print("\nThis username is already taken!\n\n\n\n\n\n");
	  	   	return null;
	  	   }
	  	}
			
			
			
		String passwordMessage = "\nPlease enter your Airline's desired password: ";
		String passwordCheckMessage = "\nPlease re-enter your Airline's desired password: ";
			
	   String password = "";
	   String passwordCheck = "";
	  	
	  	while(true) {
	   		
	  	// Checks to make sure the passwords they entered match
	  	   try {
	   	   	

	  			System.out.print(passwordMessage);
	  			password = scanner.nextLine().trim();
	   			
	  			System.out.print(passwordCheckMessage);
	  			passwordCheck = scanner.nextLine().trim();
	   	   	
	  	   	if (!password.equals(passwordCheck)) {
					
	  	   		throw new DifferentPasswordException();
				}
	
	  	   	break;
	      }
	   // If an exception is thrown, ask for passwords again
	      catch(DifferentPasswordException e) {
	   	   	
	      	System.out.print("The passwords you entered don't match!\n");
	      }
	   }
	   	
		return new Airline(name, username, password);
	}

	/**
	 * Finds the Passenger in the system and returns them. Returns null if no Passenger was found.
	 * @param username The username of the Passenger trying to log in.
	 * @param password The password of the Passenger trying to log in.
	 */
	private Passenger findPassenger(String username, String password) {
		
	// Gathers all of the Passenger accounts
		ArrayList<Passenger> allPassengers = database.getAllPassengers();
		
	// The Passenger to return
		Passenger potentialPassenger = null;
		
	// Tries to find the given info in the database
		for (int i = 0; i < allPassengers.size(); i++) {
			
			if (allPassengers.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim()) &&
					allPassengers.get(i).getPassword().toLowerCase().trim().equals(password.toLowerCase().trim())) {
				
				potentialPassenger = allPassengers.get(i);
			}
		}
			
		return potentialPassenger;
	}

	/**
	 * Finds the Airline in the system and returns them. Returns null if no Airline was found.
	 * @param username The username of the Airline trying to log in.
	 * @param password The password of the Airline trying to log in.
	 */
	private Airline findAirline(String username, String password) {
		
	// Gathers all of the Passenger accounts
		ArrayList<Airline> allAirlines = database.getAllAirlines();
		
	// The Passenger to return
		Airline potentialAirline = null;
		
	// Tries to find the given info in the database
		for (int i = 0; i < allAirlines.size(); i++) {
			
			if (allAirlines.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim()) && 
					allAirlines.get(i).getPassword().toLowerCase().trim().equals(password.toLowerCase().trim())) {
				
				potentialAirline = allAirlines.get(i);
			}
		}
			
		return potentialAirline;
	}
	
	/**
	 * Starts the Menu that is presented to the user and asks they which action
	 * they would like to take.
	 * @param database The Database to be used in the system.
	 */
	public void startMenu() {
		
	// Store a variable used to end the loop
		int kill = 1;
				
		System.out.print("Main Menu\n=========\n");
		do {
		// Capture the user's pattern choice
			String menuMessage = "Choose one of the following options:\n\nLI) Log into an existing account\n"
					+ "RP) Register a new account as a Passenger\n"
					+ "RA) Register a new account as an Airline\n";
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
			
			
			// Log In
				case "li":
					
					String logInMessage = "\nAre you an Airline or a Passenger: ";
					
			      String airlineOrPassenger = "";
			   	while(true) {
			   		
			   	// Checks to make sure the passwords they entered match
			   	   try {
			   	   	
			   	   // Asks the user if they are a passenger or an Airline
			   			System.out.print(logInMessage);
			   			airlineOrPassenger = scanner.nextLine().trim();
			   	   	
			   		// If they are a passenger, continues in this statement
			   	   	if (airlineOrPassenger.toLowerCase().trim().equals("passenger")) {
								
			   	   	// The passenger to log in
						      Passenger possiblePassenger = null;
			   	   		
			   	   	// Gathers the Passenger's log in information
			   	   		while (true) {

				   	   		try {
				   	   			
				   	   			System.out.print("\nPlease enter your username: ");
					   	   		String username = scanner.nextLine().trim();
					   	   		
					   	   		System.out.print("\nPlease enter your password: ");
					   	   		String password = scanner.nextLine().trim();
					   	   	
					   	   	// Tries to find the passenger in the database
					   	   		possiblePassenger = findPassenger(username, password);
					   	   		
					   	   	// If they are not found, throws and exception
					   	   		if (possiblePassenger == null) {
											
					   	   			throw new NoSuchElementException();
										}
					   	   		break;
				   	   		}
				   	   		catch (NoSuchElementException e) {
				   	   			
				   	   			System.out.print("\nYour account was not found!\n\n");
									}
								}

			   	   	// Once they have found their account, logs them into PRM
			   	   		System.out.print("\n\n\n\n\nPassenger Menu\n==============\n");
						   	PassengerMenu pm = new PassengerMenu(this.database, possiblePassenger);
						   	pm.startMenu();
							}
			   	   // If they are an Airline, continues in this statement
			   	   	else if (airlineOrPassenger.toLowerCase().trim().equals("airline")) {
								
				   	   	// The passenger to log in
							      Airline possibleAirline = null;
								
				   	   	// Gathers the Airline's log in information
				   	   		while (true) {

					   	   		try {
					   	   			
					   	   			System.out.print("\nPlease enter your Airline's username: ");
						   	   		String username = scanner.nextLine().trim();
						   	   		
						   	   		System.out.print("\nPlease enter your Airline's password: ");
						   	   		String password = scanner.nextLine().trim();
						   	   	
						   	   	// Tries to find the passenger in the database
						   	   		possibleAirline = findAirline(username, password);
						   	   		
						   	   	// If they are not found, throws and exception
						   	   		if (possibleAirline == null) {
												
						   	   			throw new NoSuchElementException();
											}
						   	   		break;
					   	   		}
					   	   		catch (NoSuchElementException e) {
					   	   			
					   	   			System.out.print("\nYour Airline's account was not found!\n\n");
										}
									}

				   	   	// Once they have found their account, logs them into PRM
				   	   		System.out.print("\n\n\n\n\nAirline Menu\n============\n");
							   	AirlineMenu am = new AirlineMenu(this.database, possibleAirline);
							   	am.startMenu();
			   	   	}
			   	   // If they entered the wrong word, throws an exception
			   	   	else throw new IllegalArgumentException();
			   	      break;
			   	   }
			   	// If an exception is thrown, ask for passwords again
			   	   catch(IllegalArgumentException e) {
			   	   	
			   	   	System.out.print("You didn't enter Passenger or Airline!\n");
			   	   }
			   	}
			   	
			   	System.out.print("\n\n\n\n\n");
			   		break;
				
							
								
								
			// Register Passenger	
				case "rp": 
					
				// Gets the current ArrayList<Passenger> in the database and adds the new Passenger to it
					ArrayList<Passenger> passengers = database.getAllPassengers();
					passengers.add(registerNewPassenger());
					database.setAllPassengers(passengers);
					System.out.print("\n\nYou were successfully registered!\n\n\n\n\n");
					
					
				// Serializes the state of the Database
					database.storeObjects();
		 					break;
				 				
				 				
				 				
		 	// Register Airline			
				case "ra":
					
				// Gets the current ArrayList<Passenger> in the database and adds the new Passenger to it
					ArrayList<Airline> airlines = database.getAllAirlines();
					
					Airline madeAirline = this.registerNewAirline();
					if (madeAirline != null) {

						airlines.add(madeAirline);
						database.setAllAirlines(airlines);
						System.out.print("\n\nYour Airline was successfully registered!\n\n\n\n\n");
					}
					
					
				// Serializes the state of the Database
					database.storeObjects();
				
							break;
										
								
								
			// If the user inputs something besides one of the given options do nothing
				default : break;
			}
					
		} while (kill == 1);
	}
	
}
