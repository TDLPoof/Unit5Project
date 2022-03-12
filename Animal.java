public class Animal {
  // instance data
  int attack;
  int energy;
  int speed;

  // constructor
  public Animal(int a, int e, int s) {
    attack = a;
    energy = e;
    speed = s;
  }

  // accessor methods
  public int getAttack() {
    return attack;
  }

  public int getEnergy() {
    return energy;
  }
  public int getSpeed() {
    return speed;
  }

  // modifier method for losing health
  public void takeDamage (int amt) {
    energy -= amt;
  }
}