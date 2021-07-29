package oopTargil;

/**
 * @author Erik Sionov
 *
 */
public class Car {

	private int number;
	private int speed;

	public Car(int number, int speed) {
		this.setNumber(number);
		this.setSpeed(speed);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) throws RuntimeException {
		if (speed > 110 || speed < 0) {
			throw new RuntimeException("speed can't be below 0 or above 110");
		}
		this.speed = speed;
	}

	/**
	 * Generates a specified amount of random cars
	 * 
	 * @param amount
	 *            the number of cars to generate
	 * @return an array of randomly generated cars
	 */
	public static Car[] randomCar(int amount) {
		Car[] cars = new Car[amount];
		for (int i = 0; i < cars.length; i++) {
			int carNumber = (int) (Math.random() * 99_000 + 100_000);
			int randomSpeed = (int) (Math.random() * 111);
			cars[i] = new Car(carNumber, randomSpeed);
		}
		return cars;
	}
	
	/**
	 * Generates a single random car
	 * 
	 * @return a single randomly generated car
	 */
	public static Car randomCar() {
		int carNumber = (int) (Math.random() * 99_000 + 100_000);
		int randomSpeed = (int) (Math.random() * 111);
		return new Car(carNumber, randomSpeed);
	}

	@Override
	public String toString() {

		return "[car number= " + number + " , speed= " + speed + "]";
	}

}
