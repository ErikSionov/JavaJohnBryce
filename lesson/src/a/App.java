package a;

public class App {
	public static void main(String[] args) {
		
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person(28, 255, "ori");
		
		p1.name = "Yossi";
		p1.age = 45;
		p1.id = 1234;
		
		p2.name = "Rafael";
		p2.age = 38;
		p2.id = 567;
		
		p3.name = "Michael";
		p3.age = 32;
		p3.id = 890;
		
		System.out.println("p1 is " + p1.name +" his age is " + p1.age +" and his ID is " + p1.id);
		System.out.println("p2 is " + p2.name +" his age is " + p2.age +" and his ID is " + p2.id);
		System.out.println("p3 is " + p3.name +" his age is " + p3.age +" and his ID is " + p3.id);
		System.out.println("p4 is " + p4.name +" his age is " + p4.age +" and his ID is " + p4.id);
		
		
	}
}
