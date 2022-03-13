public class Predator extends Animal {
  // predator needs a certain amount of meat to not die
  // and a value for how much meat it has eaten today
  // also it needs to know how much energy it *can* have in order to regenerate health
  // also also it needs to know how many days it has been alive
  // also also also it needs to know how many hunts it can do in a day
  // and how many it can do
  int meatQuota;
  int meatEatenToday;
  int maxHealth;
  int daysAlive;
  int huntsPerDay;
  int huntsLeft;

  // constructor (using super for shorthand)
  public Predator(int a, int h, int s, int mq, int hpd) {
    super (a, h, s);
    meatQuota = mq;
    meatEatenToday = 0;
    maxHealth = h;
    daysAlive = 0;
    huntsPerDay = hpd;
    huntsLeft = huntsPerDay;
  }

  // accessor methods
  public int getMeatQuota() {
    return meatQuota;
  }

  public int getMeatEatenToday() {
    return meatEatenToday;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public int getHuntsLeft() {
    return huntsLeft;
  }

  public int getDaysAlive() {
    return daysAlive;
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
    health = maxHealth;
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
    while (target.getHealth() > 0 && health > 0) {
      // if enemy is faster, they attack first
      // otherwise you attack first
      if (speed < target.getSpeed()) {
        takeDamage(target.getAttack());
        if (health <= 0) break;
        target.takeDamage(attack);
        if (target.getHealth() <= 0) break;
      }
      else {
        target.takeDamage(attack);
        if (target.getHealth() <= 0) break;
        takeDamage(target.getAttack());
        if (health <= 0) break;
      }
    }
    if (health <= 0) { // clause in which player dies
      System.out.println("You died.");
      System.out.print("You surived " + daysAlive + " days, ");
      System.out.print("fighting " + (huntsPerDay - huntsLeft - 1) + " times ");
      System.out.println("on your last day.");
      return;
    }
    // clause in which prey dies
    System.out.println("Hunt successful!");
    huntsLeft--;
    meatEatenToday += target.getMeatAmount();
    health += target.getMeatAmount() / 2;
    System.out.print("Meat quota: ");
    System.out.println("" + meatEatenToday + " / " + meatQuota);
    System.out.println("Health: " + health + " / " + maxHealth);
    System.out.println();
  }

  public String toString() {
    String s = "Health: " + health + " / " + maxHealth + "\n";
    s += "Meat Quota: " + meatEatenToday + " / " + meatQuota + "\n";
    s += "Attack: " + attack + "\n";
    s += "Speed: " + speed + "\n";
    return s;
  }
}