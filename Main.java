import java.util.Random;
import java.util.Arrays;
class Main {
  static Random random = new Random();

  // hardcoded because the initial seed of the population does not need to be changed
  public Prey generatePrey() {
    int atk = random.nextInt(10, 25);
    int nrg = random.nextInt(50, 75);
    int spd = random.nextInt(10, 25);
    int mmt = random.nextInt(10, 25);
  }
  public static void main(String[] args) {
    Predator player = new Predator(25, 100, 50, 40, 5);
  }
}