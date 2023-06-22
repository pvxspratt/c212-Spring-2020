import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Dylan Herthoge
 * Stores all of the objects used in the system.
 */
@SuppressWarnings("serial")
public class Database implements java.io.Serializable {

	/**
	 * Stores all of the registered Passengers.
	 */
	private ArrayList<Passenger> allPassengers;
	
	/**
	 * Stores all of the registered Airlines.
	 */
	private ArrayList<Airline> allAirlines;
	
	/**
	 * Stores all current Reviews in the system.
	 */
	private ArrayList<Review> allReviews;
	
	/**
	 * Stores all current Flights in the system.
	 */
	private ArrayList<Flight> allFlights;

	public Database() {
		
		super();
		
		this.allPassengers = new ArrayList<Passenger>();
		this.allAirlines = new ArrayList<Airline>();
		this.allReviews = new ArrayList<Review>();
		this.allFlights = new ArrayList<Flight>();
	}

	/**
	 * Gets Passengers stored in allPassengers.
	 * @return A ArrayList<Passenger>.
	 */
	public ArrayList<Passenger> getAllPassengers() {
		
		ArrayList<Passenger> copy = new ArrayList<Passenger>();
		
		for (int i = 0; i < this.allPassengers.size(); i++) {
			
			copy.add(this.allPassengers.get(i));
		}
		
		return copy;
	}

	/**
	 * Sets allPassengers to the given ArrayList<Passenger>.
	 * @param allPassengers A ArrayList<Passenger>.
	 */
	public void setAllPassengers(ArrayList<Passenger> allPassengers) {

		ArrayList<Passenger> copy = new ArrayList<Passenger>();
		
		for (int i = 0; i < allPassengers.size(); i++) {
			
			copy.add(allPassengers.get(i));
		}
		
		this.allPassengers = copy;
	}

	/**
	 * Gets Airlines stored in allAirlines.
	 * @return A ArrayList<Airline>.
	 */
	public ArrayList<Airline> getAllAirlines() {
		
		ArrayList<Airline> copy = new ArrayList<Airline>();
		
		for (int i = 0; i < this.allAirlines.size(); i++) {
			
			copy.add(this.allAirlines.get(i));
		}
		
		return copy;
	}

	/**
	 * Sets allAirlines to the given ArrayList<Airline>.
	 * @param allAirlines A ArrayList<Airline>.
	 */
	public void setAllAirlines(ArrayList<Airline> allAirlines) {

		ArrayList<Airline> copy = new ArrayList<Airline>();
		
		for (int i = 0; i < allAirlines.size(); i++) {
			
			copy.add(allAirlines.get(i));
		}
		
		this.allAirlines = copy;
	}

	/**
	 * Gets Reviews stored in allReviews.
	 * @return A ArrayList<Review>.
	 */
	public ArrayList<Review> getAllReviews() {
		
		ArrayList<Review> copy = new ArrayList<Review>();
		
		for (int i = 0; i < this.allReviews.size(); i++) {
			
			copy.add(this.allReviews.get(i));
		}
		
		return copy;
	}

	/**
	 * Sets allReviews to the given ArrayList<Review>.
	 * @param allReviews A ArrayList<Review>.
	 */
	public void setAllReviews(ArrayList<Review> allReviews) {
		
		ArrayList<Review> copy = new ArrayList<Review>();
		
		for (int i = 0; i < allReviews.size(); i++) {
			
			copy.add(allReviews.get(i));
		}
		
		this.allReviews = copy;
	}

	/**
	 * Gets Flights stored in allFlights.
	 * @return A ArrayList<Flight>.
	 */
	public ArrayList<Flight> getAllFlights() {
		
		ArrayList<Flight> copy = new ArrayList<Flight>();
		
		for (int i = 0; i < this.allFlights.size(); i++) {
			
			copy.add(this.allFlights.get(i));
		}
		
		return copy;
	}

	/**
	 * Sets allFlights to the given ArrayList<Flight>.
	 * @param allFlights A ArrayList<Flight>.
	 */
	public void setAllFlights(ArrayList<Flight> allFlights) {
		
		ArrayList<Flight> copy = new ArrayList<Flight>();
		
		for (int i = 0; i < allFlights.size(); i++) {
			
			copy.add(allFlights.get(i));
		}
		
		this.allFlights = copy;
	}
	
	/**
	 * Recreates the copies of the Database instance variables stored in
	 * the "database.txt".
	 */
	public void getStoredObjects() {
		
		Database database = null; 
		  
      // Deserialization 
      try {    
      // Reading the object from a file 
      	FileInputStream file = new FileInputStream("database.txt"); 
      	ObjectInputStream in = new ObjectInputStream(file); 
      	
      	// Method for deserialization of object 
      	database = (Database)in.readObject(); 
      	
      	in.close(); 
      	file.close(); 
      } 
      catch(IOException ex) { 
      	
          System.out.println("IOException is caught"); 
      } 
      catch(ClassNotFoundException ex) { 
      	
          System.out.println("ClassNotFoundException is caught"); 
      } 
      
      this.allPassengers = database.allPassengers;
      this.allAirlines = database.allAirlines;
      this.allReviews = database.allReviews;
      this.allFlights = database.allFlights;
	}
	
	/**
	 * Stores the copies of the Database instance variables stored in
	 * the "database.txt".
	 */
	public void storeObjects() {
		       
      // Serialization  
      try {    
      	
      //Saving of object in a file 
      	FileOutputStream file = new FileOutputStream("database.txt"); 
      	ObjectOutputStream out = new ObjectOutputStream(file); 
            
      // Method for serialization of object 
      	out.writeObject(this); 
            
      	out.close(); 
      	file.close();

      } 
      catch(IOException ex) { 
      	
      	System.out.println("IOException is caught"); 
      } 
	}
}
