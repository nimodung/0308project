package exam06;

public class VehicleExam {

	public static void main(String[] args) {
		
		Driver driver = new Driver();
		TaxiDriver taxidriver = new TaxiDriver();
		
		Bus bus = new Bus();
		Taxi taxi = new Taxi();
		
		driver.drive(bus);
		driver.drive(taxi);
		
		taxidriver.drive(taxi);
		
		/*
		 * Vehicle vehicle = new Bus(); // vehicle.checkFare(); -> X Bus bus1 =
		 * (Bus)vehicle; bus1.checkFare();
		 */
	}
}
