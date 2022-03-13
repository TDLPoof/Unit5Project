import java.util.*;
import java.text.DecimalFormat;

class Main {
  static Random random = new Random();
  static Scanner reader = new Scanner(System.in);
  static ArrayList<Prey> population = new ArrayList<Prey>();
  static DecimalFormat df = new DecimalFormat("#.##");

  public static void goHunting(Predator caster) {
    System.out.println("Enter the index of the creature to hunt (0-" + (population.size() - 1) + ")");
    int selection = 0;
    int index = -1;
    while (selection == 0) {
      while (index < 0 || index >= population.size()) {
        index = reader.nextInt();
        if (index < 0 || index >= population.size())
          System.err.println("Error: specified index out of bounds.");
        }
        System.out.println("Will you hunt this animal? (any for yes, 0 for no)");
        System.out.println(population.get(index).toString());
        selection = reader.nextInt();
        if (selection == 0) {
          index = -1;
          System.out.println("Enter the index of the creature to hunt (0-" + (population.size() - 1) + ")");
        }
        else {
          caster.fightPrey(population.get(index));
          if (population.get(index).getHealth() <= 0)
            population.remove(index);
        }
      }
    }

  public static void rerollPopulation() {
    ArrayList<Prey> newPop = new ArrayList<Prey>();
    for (int i = 0; i < population.size(); i++) {
      int index = i;
      while (index == i) {
        index = random.nextInt(population.size());
      }
      newPop.add(population.get(i).breed(population.get(index)));
    }
  }
  
  public static String getAvgPrey() {
    int sumHealth = 0;
    int sumMeat = 0;
    int sumAttack = 0;
    int sumSpeed = 0;
    for (int i = 0; i < population.size(); i++) {
      sumHealth += population.get(i).getHealth();
      sumMeat += population.get(i).getMeatAmount();
      sumAttack += population.get(i).getAttack();
      sumSpeed += population.get(i).getSpeed();
    }
    String health = df.format(((double)sumHealth) / population.size());
    String meatAmount = df.format(((double)sumMeat) / population.size());
    String attack = df.format(((double)sumAttack) / population.size());
    String speed = df.format(((double)sumSpeed) / population.size());
    String s = "Health: " + health + "\n";
    s += "Meat Amount: " + meatAmount + "\n";
    s += "Attack: " + attack + "\n";
    s += "Speed: " + speed + "\n";
    return s;
  }
  
  // hardcoded because the initial seed of the population does not need to be changed
  public static Prey generatePrey() {
    int atk = 10 + random.nextInt(15);
    int nrg = 50 + random.nextInt(25);
    int spd = 10 + random.nextInt(15);
    int mmt = 10 + random.nextInt(15);
    return new Prey(atk, nrg, spd, mmt);
  }
  public static void main(String[] args) {
    Predator player = new Predator(25, 100, 50, 60, 5);
    for (int i = 0; i < 20; i++) {
      population.add(generatePrey());
    }
    int choice = -1;
    while (choice != 0 && player.getHealth() > 0) {
      System.out.println("Enter your choice:");
      System.out.println("\t1. View your own stats.");
      System.out.println("\t2. Hunt an animal.");
      System.out.println("\t3. View the stats of the average prey.");
      System.out.println("\t4. Sleep the night away.");
      System.out.println("\t0. Quit.");
      choice = reader.nextInt();
      switch (choice) {
        case 0:
          break;
        case 1: 
          System.out.println("\n" + player.toString() + "\n");
          break;
        case 2:
          goHunting(player);
          break;
        case 3:
          System.out.println(getAvgPrey());
          break;
        case 4:
          if (player.getMeatEatenToday() < player.getMeatQuota()) {
            System.out.println("You died.");
            System.out.print("You surived " + player.getDaysAlive() + " days, ");
            System.out.println("but you starved to death.");
            choice = 0;
          }
          else {
            rerollPopulation();
            System.out.println("The surviving animals reproduce. The average prey is now:");
            System.out.println(getAvgPrey());
            player.newDay();
          }
            break;
        default:
          System.err.println("Error: outside of specified bounds.");
          break;
      }
    }
  }
}