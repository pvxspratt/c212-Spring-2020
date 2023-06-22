import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AirlineReviewMenuJUnits {

	Airline delta = new Airline("Delta", "delta", "password");
	
	Airline united = new Airline("United", "united", "password");
	
	Airline american = new Airline("American", "american", "password");
	
	Passenger dherthoge = new Passenger("Dylan", "dherthoge", "password", 0, null, null, null);
	
	Passenger jmangan = new Passenger("Mango", "jmangan", "password", 0, null, null, null);
	
	Passenger pspratt = new Passenger("Phoebe", "pspratt", "password", 0, null, null, null);
	
	Review rDylan = new Review(dherthoge, "Delta", "I loved it", 1);
	
	Review rDylan1 = new Review(dherthoge, "United", "I loved it", 2);
	
	Review rDylan2 = new Review(dherthoge, "Delta", "I loved it", 4);
	
	Review rMango = new Review(jmangan, "American", "I loved it", 3);
	
	Review rMango1 = new Review(jmangan, "Delta", "I loved it", 4);
	
	Review rMango2 = new Review(jmangan, "Delta", "I loved it", 5);
	
	Review rPhoebe = new Review(pspratt, "United", "I loved it", 5);
	
	Review rPhoebe1 = new Review(pspratt, "American", "I loved it", 2);
	
	Review rPhoebe2 = new Review(pspratt, "Delta", "I loved it", 1);
	
	@Test
	void test0() {

		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, delta);
		
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan);
		testOutput.add(rDylan2);
		testOutput.add(rMango1);
		testOutput.add(rMango2);
		testOutput.add(rPhoebe2);
		
		assertEquals(testOutput, amr.getAirlineReviews());
	}
	
	@Test
	void test1() {

		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, united);
		
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan1);
		testOutput.add(rPhoebe);
		
		assertEquals(testOutput, amr.getAirlineReviews());
	}
	
	@Test
	void test2() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, delta);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan);
		testOutput.add(rDylan2);

		assertEquals(amr.filterByPassenger("Dylan"), testOutput);
	}
	
	@Test
	void test3() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, united);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rPhoebe);

		assertEquals(amr.filterByPassenger("Phoebe"), testOutput);
	}
	
	@Test
	void test4() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, delta);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan2);
		testOutput.add(rMango1);
		testOutput.add(rMango2);

		assertEquals(amr.filterByStars(3, "above"), testOutput);
	}
	
	@Test
	void test5() {
		
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(rDylan);
		reviews.add(rDylan1);
		reviews.add(rDylan2);
		reviews.add(rMango);
		reviews.add(rMango1);
		reviews.add(rMango2);
		reviews.add(rPhoebe);
		reviews.add(rPhoebe1);
		reviews.add(rPhoebe2);
		
		Database database = new Database();
		database.setAllReviews(reviews);
		
		AirlineReviewMenu amr = new AirlineReviewMenu(database, united);
		
		ArrayList<Review> testOutput = new ArrayList<Review>();
		testOutput.add(rDylan1);

		assertEquals(amr.filterByStars(3, "below"), testOutput);
	}

}
