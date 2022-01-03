package characterCreator;

public enum Race {
	HUMAN, DWARF, ELF, ORC, HALFLING;
	
	public static Race randomRace() {
		int n = (int)(Math.random()*Race.values().length);
		
		return Race.values()[n];
	}
}
