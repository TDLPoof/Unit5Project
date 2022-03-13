import java.util.Random;

public class Prey extends Animal {
	// static random object for breeding
	// and static int representing mutation strength;
	static Random GENE_WIZARD = new Random();
	static int GENETIC_VARIATION = 5;

	// prey needs to have an amount of meat it drops on kill
	int meatAmount;

	// constructor (using super for shorthand)
	public Prey(int a, int h, int s, int m) {
		super(a, h, s);
		meatAmount = m;
	}

	// accessor for meat amount
	public int getMeatAmount() {
		return meatAmount;
	}

	// helper method for breeding two stats
	public int breedStat(int a, int b) {
		int statDelta = 0;
		if (a != b) {
			statDelta = GENE_WIZARD.nextInt(Math.abs(b - a));
		}
		int newStat = a + statDelta;
		newStat += (GENETIC_VARIATION * -1) + GENE_WIZARD.nextInt(2 * GENETIC_VARIATION + 1);
		return newStat;
	}

	public String toString() {
		String s = "Health: " + health + "\n";
		s += "Meat Amount: " + meatAmount + "\n";
		s += "Attack: " + attack + "\n";
		s += "Speed: " + speed + "\n";
		return s;
	}

	// method that breeds two prey items with each other
	// the mechanism behind the genetic algorithm of the game
	public Prey breed(Prey other) {
		int newAttack = breedStat(attack, other.getAttack());
		int newHealth = breedStat(health, other.getHealth());
		int newSpeed = breedStat(speed, other.getSpeed());
		int newMeatAmt = breedStat(meatAmount, other.getMeatAmount());
		return new Prey(newAttack, newHealth, newSpeed, newMeatAmt);
	}
}