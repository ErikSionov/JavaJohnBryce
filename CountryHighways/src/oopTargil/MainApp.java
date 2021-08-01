package oopTargil;

/**
 * @author Erik Sionov
 *
 */
public class MainApp {
	public static void main(String[] args) {
		// Instantiate a country class
		Country israel = new Country("Israel");
		// Instantiate a highway array
		Highway[] highways = new Highway[5];

		// randomize highways to array
		for (int i = 0; i < highways.length; i++) {
			int rNum = (int) (Math.random() * 101);
			highways[i] = new Highway(Highway.randomHighwayName(), Car.randomCar(rNum));
		}

		// set highways array into country
		israel.setHighways(highways);

		// print all highways and their cars
		print(highways);
		System.out.println();

		// print number of cars in country
		System.out.println("number of cars in country is: " + israel.numOfCarsInCountry());
		System.out.println();

		// print all country highway information
		// refactor into Country class for encapsulation
		israel.countryHighwaysInformation();

		// print highway with most traffic and top average speed
		// refactor into Country class for encapsulation
		israel.countryMostTrafficAndAverageSpeed();

	}

	/**
	 * Print method to print large formated arrays
	 * 
	 * @param <T>
	 *            generic array of elements
	 * @param arr
	 *            array of elements to print
	 */
	public static <T> void print(T[] arr) {
		for (T t : arr) {
			System.out.println(t.toString());
		}
	}

}
