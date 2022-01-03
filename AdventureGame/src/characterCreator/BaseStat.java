package characterCreator;

import java.util.HashMap;
import java.util.Iterator;

public class BaseStat {
	private String name;
	private int value;
	int totalModifiers;
	private HashMap<String, Modifier> modifiers = new HashMap<>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public HashMap<String, Modifier> getModifiers() {
		return this.modifiers;
	}

	public void addModifier(String name, int bonus) {
		
		if(!modifiers.containsKey(name)) {
			modifiers.put(name, new Modifier(name, bonus));
		}
		String bonusType = (bonus >= 0) ? "+" : "";
		System.out.println("Modifier added: " + name + " | Bonus: " + bonusType + bonus);
	}

	public int calculateModifiers() {
		int sum = 0;

		Iterator<Modifier> it = modifiers.values().iterator();
		
		while(it.hasNext()) {
			int mod = it.next().getValue();
			sum += mod;
		}
		
		return sum;
	}

	@Override
	public String toString() {
		String statInfo = "|" + this.getName() + " \t| Base: " + this.getValue() + " \t| Bonus: "
				+ this.calculateModifiers();

		return statInfo;
	}
}
