package a;

public class Homework {
	
	public static void main(String[] args) {
		
		Book harryPotter = new Book();
		
		harryPotter.author = "JK Rowling";
		harryPotter.price = 45;
		harryPotter.title = "Harry Potter";
		
		System.out.println("book name " + harryPotter.title);
		System.out.println("author " + harryPotter.author);
		System.out.println("price " + harryPotter.price);
		
		System.out.println("\n");
		Car toyota = new Car();
		
		toyota.brand = "Toyota";
		toyota.color = "white";
		toyota.km = 50440;
		toyota.model = "Prius";
		toyota.number = 564446;
		toyota.speed = 150;
		toyota.year = 2017;
		
		System.out.println("brand " + toyota.brand);
		System.out.println("model " + toyota.model);
		System.out.println("color " + toyota.color);
		System.out.println("number " + toyota.number);
		System.out.println("year " + toyota.year);
		System.out.println("max speed " + toyota.speed);
		System.out.println("kilometrage " + toyota.km);
		
	}
}
