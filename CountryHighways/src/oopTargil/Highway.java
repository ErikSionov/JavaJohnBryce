package oopTargil;

import java.util.Arrays;

public class Highway {
	private String name;
	private Car[] cars;

	public Highway(String name, Car[] cars) {
		this.setName(name);
		this.setCars(cars);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car[] getCars() {
		return cars;
	}
	public void setCars(Car[] cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "[name=" + name + " Highway, cars=" + Arrays.toString(cars) + "]";
	}

	public int getNumOfCars() {
		return this.getCars().length;
	}

	public int getAverageSpeedOfCars() throws RuntimeException {
		int sum = 0;
		for (int i = 0; i < cars.length; i++) {
			sum += cars[i].getSpeed();
		}
		int d = getNumOfCars();
		if (d <= 0) {
			throw new RuntimeException("zero cars on the road");
		}
		return sum / d;
	}
	
	public int getFastestCar() {
		int max = cars[0].getSpeed();
		for (int i = 1; i < cars.length; i++) {
			if(cars[i].getSpeed() > max) {
				max = cars[i].getSpeed();
			}
		}
		return max;
	}
	
	public static String randomHighwayName() {
		String[] suffix = {"Pass", "Work", "Underbridge", "Second", "First", "Undermount", "City", "Tree"};
		String[] names = {"Great", "Main", "Latte", "Prince", "Bibi", "Cohen", "Israel", "Creature"};
		int r = (int) (Math.random() * names.length);
		int r2 = (int) (Math.random() * suffix.length);
		return names[r] + " " + suffix[r2];
	}
}
