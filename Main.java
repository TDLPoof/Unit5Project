import java.util.Random;
import java.util.Arrays;

class Main {
  static Random random = new Random();

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
    Prey prey;
    for (int i = 0; i < 5; i++) {
      if (player.getEnergy() <= 0) break;
      // take this out later, just testing to see if quota mechanics work
      if (player.getMeatEatenToday() >= player.getMeatQuota()) {
        System.out.println("You feel full and do not need to eat.\n");
        continue;
      }
      prey = generatePrey();
      player.fightPrey(prey);
    }
    if (player.getMeatEatenToday() < player.getMeatQuota() && player.getEnergy() > 0) {
      System.out.println("u ded");
    }
  }
}