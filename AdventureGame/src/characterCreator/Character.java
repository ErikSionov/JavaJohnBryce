package characterCreator;

public class Character {
	private String name;
	private Stat[] stats;
	private String charClass;
	private Race charRace;

	public Character(String name) {
		setName(name);
		stats = new Stat[6];
		for (int i = 0; i < stats.length; i++) {
			stats[i] = new Stat();
		}
		setStats();
	}

	public void setStats() {
		// Randomize stat base value from 8 to 18
		// roll 3d6 and if roll > 8 insert into next stat;
		stats[0].setName("Strength");
		stats[1].setName("Dexterity");
		stats[2].setName("Constitution");
		stats[3].setName("Intelligence");
		stats[4].setName("Wisdom");
		stats[5].setName("Charisma");
		for (int i = 0; i < stats.length; i++) {
			while (stats[i].getValue() < 8) {
				stats[i].setValue(DiceRoller.roll(3, 6));
			}
		}
	}

	public void setRace(Race race) {
		this.charRace = race;
	}

	public String getName() {
		return this.name;
	}

	public Stat[] getStats() {
		return this.stats;
	}

	public void getStatsInfo() {
		System.out.println("======== stats ========");
		for (Stat stat : stats) {
			if (stat != null) {
				System.out.println(stat.toString());
			}
		}
		System.out.println("======== stats end ========");
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStats(Stat... stats) {
		this.stats = new Stat[stats.length];

		for (int i = 0; i < stats.length; i++) {
			this.stats[i] = stats[i];
		}
	}

	public Race getRace() {
		return this.charRace;
	}
	
	public void calculateMod() {
		
	}
}
