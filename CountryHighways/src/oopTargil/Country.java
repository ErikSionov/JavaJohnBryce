package oopTargil;

/**
 * @author Erik Sionov
 *
 */
public class Country {
	private String name;
	private Highway[] highways;
	
	public Country(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Highway[] getHighways() {
		return highways;
	}
	public void setHighways(Highway[] highways) {
		this.highways = highways;
	}
	
	public int numOfCarsInCountry() {
		int sum = 0;
		for (int i = 0; i < this.getHighways().length; i++) {
			sum += this.getHighways()[i].getCars().length;
		}
		return sum;
	}
	
	/**print into console important information about highways in country
	 * like number of cars on each highway, average speed of cars and maximum speed of cars
	 */
	public void countryHighwaysInformation() {
		for (int i = 0; i < this.getHighways().length; i++) {
			// print number of cars on each highway
			Highway hw = this.getHighways()[i];
			System.out.println(hw.getName() + " Highway has number of cars: " + hw.getNumOfCars());
			// print average speed of cars on each highway
			System.out.println(hw.getName() + " Highway average speed of cars is: " + hw.getAverageSpeedOfCars());
			// print maximum speed of cars on the highway
			System.out.println(hw.getName() + " Highway fastest car is: " + hw.getFastestCar());
		}
	}
	
	/**print into console important information about highways in country
	 * which highway has most traffic and top average speed
	 */
	public void countryMostTrafficAndAverageSpeed() {
		int topTraffic = this.getHighways()[0].getNumOfCars();
		int topAverageSpeed = this.getHighways()[0].getAverageSpeedOfCars();
		int topTrafficIndex = 0;
		int topAverageSpeedIndex = 0;

		for (int i = 1; i < this.getHighways().length; i++) {
			Highway hw = this.getHighways()[i];
			if (hw.getAverageSpeedOfCars() > topAverageSpeed) {
				topAverageSpeed = hw.getAverageSpeedOfCars();
				topAverageSpeedIndex = i;
			}
			if (hw.getNumOfCars() > topTraffic) {
				topTraffic = hw.getNumOfCars();
				topTrafficIndex = i;
			}
		}
		System.out.println();
		System.out.println("top traffic is on: " + this.getHighways()[topTrafficIndex].getName() + " Highway.");
		System.out.println("top average speed is on: " + this.getHighways()[topAverageSpeedIndex].getName() + " Highway.");
	}
}
