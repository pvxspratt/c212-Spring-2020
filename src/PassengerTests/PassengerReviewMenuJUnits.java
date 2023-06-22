import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PassengerReviewMenuJUnits {
	
	Passenger dherthoge = new Passenger("Dylan", "dherthoge", "password", 0, null, null, null);
	
	Passenger jmangan = new Passenger("Mango", "jmangan", "password", 0, null, null, null);
	
	Passenger pspratt = new Passenger("Phoebe", "pspratt", "password", 0, null, null, null);
	
	Review rDylan = new Review(dherthoge, "Delta", "I loved it", 1);
	
	Review rDylan1 = new Review(dherthoge, "United", "I loved it", 2);
	
	Review rMango = new Review(jmangan, "American", "I loved it", 3);
	
	Review rMango1 = new Review(jmangan, "Delta", "I loved it", 4);
	
	Review rPhoebe = new Review(pspratt, "United", "I loved it", 5);
	
	Review rPhoebe1 = new Review(pspratt, "American", "I loved it", 2);
	
	@Test
	void test0() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan);
		testOutput.add(rMango1);

		assertEquals(pmr.filterByAirline("Delta"), testOutput);
	}
	
	@Test
	void test1() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan1);
		testOutput.add(rPhoebe);

		assertEquals(pmr.filterByAirline("United"), testOutput);
	}
	
	@Test
	void test3() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan);
		testOutput.add(rDylan1);

		assertEquals(pmr.filterByPassenger("Dylan"), testOutput);
	}
	
	@Test
	void test4() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rPhoebe);
		testOutput.add(rPhoebe1);

		assertEquals(pmr.filterByPassenger("Phoebe"), testOutput);
	}
	
	@Test
	void test5() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan);
		testOutput.add(rDylan1);
		testOutput.add(rMango);
		testOutput.add(rPhoebe1);

		assertEquals(pmr.filterByStars(3, "below"), testOutput);
	}
	
	@Test
	void test6() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rMango);
		testOutput.add(rMango1);
		testOutput.add(rPhoebe);

		assertEquals(pmr.filterByStars(3, "above"), testOutput);
	}

	
	@Test
	void test7() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);

		assertEquals(pmr.search(), "\n" + 
				"Reviews:\n" + 
				"\n" + 
				"Passenger: Dylan\n" + 
				"Airline: Delta\n" + 
				"Stars: 1\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Dylan\n" + 
				"Airline: United\n" + 
				"Stars: 2\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Mango\n" + 
				"Airline: American\n" + 
				"Stars: 3\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Mango\n" + 
				"Airline: Delta\n" + 
				"Stars: 4\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Phoebe\n" + 
				"Airline: United\n" + 
				"Stars: 5\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Phoebe\n" + 
				"Airline: American\n" + 
				"Stars: 2\n" + 
				"\n" + 
				"I loved it\n\n\n\n\n");
	}
	
	@Test
	void test8() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rPhoebe1);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);

		assertEquals(pmr.search(), "\n" + 
				"Reviews:\n" + 
				"\n" + 
				"Passenger: Dylan\n" + 
				"Airline: Delta\n" + 
				"Stars: 1\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"Passenger: Phoebe\n" + 
				"Airline: American\n" + 
				"Stars: 2\n" + 
				"\n" + 
				"I loved it\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"");
	}
	
	@Test
	void test9() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();

		Airline delta = new Airline("Delta", "delta", "password");
		Flight flight = new Flight(delta, 123, "Bikini Bottom", "Rock Bottom", 5, System.currentTimeMillis(),
				1, true, "April7,2020", 20, 5, 5, 5, 5);
		Ticket deltaTicket = new Ticket(5, "economy", flight, delta, System.currentTimeMillis());
		ArrayList<Ticket> oldTickets = new ArrayList<Ticket>();
		oldTickets.add(deltaTicket);
		
		dherthoge.setOldTickets(oldTickets);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), dherthoge);
		pmr.setReviews(reviews);
		
		

		assertEquals(pmr.checkPreviousFlights("delta"), true);
	}
	
	@Test
	void test10() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		ArrayList<Ticket> oldTickets = new ArrayList<Ticket>();
		pspratt.setOldTickets(oldTickets);
		
		PassengerReviewMenu pmr = new PassengerReviewMenu(new Database(), pspratt);
		pmr.setReviews(reviews);
		
		

		assertEquals(pmr.checkPreviousFlights("delta"), false);
	}

	
}
