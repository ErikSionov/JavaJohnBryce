package characterCreator;

public class Stat extends BaseStat {
	public Stat() {
	}

	public Stat(String name, int value) {
		this.setName(name);
		this.setValue(value);
	}
	
	public int mod() {
		int totalMod = (super.getValue() + calculateModifiers())/2-5;
		return totalMod;
	}
}
