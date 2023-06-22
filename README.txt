# c212-Spring-2020
C212 Final Project: Airline TIcket Booking System


Testing
=======
In order to test the program, MenuDriver should be used.  In the state that our project was turned in
lines 110-113 are left uncommented (i.e. they will be executed).  When these lines are uncommented, 
the objects in the Database class will be set the the Airines, Passengers, and Flights that are
initialized in MenuDriver, effectively turning off state persistence.  In order to have state persistence
comment these lines out and when the program is run, the objects that were stored in the Database class
before the program was terminated the previous time will be read from database.txt (there is no need to 
create a database.txt manually, one will be made the first time the program is run on your system).

Other than state persistence, most prompts are self explanitory and allow for (relatively) easy navigation
of the system.

In order to pay with miles, the user dylan with password p has 20000 miles to spend, otherwise when a passengers
books a flight and it is canceled by the airline, the passenger is credited with the number of miles the flight 
was going to travel (1 mile = $1).

All of the users you can log in as when running the file with lines 110-113 uncommented are as follows
Passenger: dylan Password: p
Passenger: mango Password: p
Passenger: phoebe Password: p
Airline: delta Password: p
Airline: american Password: p
Airline: united Password: p


Passengers are prompted for meal preferences if the flight is over 1500 miles.


More passengers and Airlines can be created, but they must have unique usernames (passwords don't have to
be 'p', we just found it convenient for testing.



JUnits
======
We have encounterd issues previously with transfering JUnit files between systems/IDEs.  Make a new JUnit
file in your IDE and copy and paste our JUnits into the new file.