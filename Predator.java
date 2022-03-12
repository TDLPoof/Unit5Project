public class Predator extends Animal {
  // predator needs a certain amount of meat to not die
  // and a value for how much meat it has eaten today
  // also it needs to know how much energy it *can* have in order to regenerate health
  // also also it needs to know how many days it has been alive
  // also also also it needs to know how many hunts it can do in a day
  // and how many it can do
  int meatQuota;
  int meatEatenToday;
  int maxEnergy;
  int daysAlive;
  int huntsPerDay;
  int huntsLeft;

  // constructor (using super for shorthand)
  public Predator(int a, int e, int s, int mq, int hpd) {
    super (a, e, s);
    meatQuota = mq;
    meatEatenToday = 0;
    maxEnergy = e;
    daysAlive = 0;
    huntsPerDay = hpd;
  }

  // accessor methods
  public int getMeatQuota() {
    return meatQuota;
  }

  public int getMeatEatenToday() {
    return meatEatenToday;
  }

  public int getMaxEnergy() {
    return maxEnergy;
  }

  public int getHuntsLeft() {
    return huntsLeft;
  }
  
  // modifier method for meat eaten today
  // also returns true to make sure that the operation completed
  // similarly to Arrays.add();
  public boolean eatMeat(int amount) {
    meatEatenToday += amount;
    return true;
  }

  // misc method for resetting things daily
  public void newDay() {
    energy = maxEnergy;
    meatEatenToday = 0;
    huntsLeft = huntsPerDay;
    daysAlive++;
  }

  // the all-important method for eating a prey item
  public void fightPrey(Prey target) {
    if (huntsLeft <= 0) { // out of hunts for the day
      System.err.println("Error: no hunts left for the day.");
      System.err.println("also how did you manage to trigger this?");
      return;
    }
    // keep the fight going until one entity dies
    while (target.getEnergy() > 0 && energy > 0) {
      
    }
    if (energy <= 0) { // clause in which player dies
      System.out.println("You died.");
      System.out.println("You surived " + daysAlive + " days.");
      return;
    }
    // clause in which prey dies
    System.out.println("Hunt successful!");
    huntsLeft--;
    System.out.print("Meat quota: ");
    System.out.println("" + meatEatenToday + " / " + meatQuota);
    System.out.println("Energy: " + energy + " / " + maxEnergy);
 }
}