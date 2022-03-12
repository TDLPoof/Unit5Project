public class Animal {
  // instance data
  int attack;
  int health;
  int speed;

  // constructor
  public Animal(int a, int h, int s) {
    attack = a;
    health = h;
    speed = s;
  }

  // accessor methods
  public int getAttack() {
    return attack;
  }

  public int getHealth() {
    return health;
  }
  public int getSpeed() {
    return speed;
  }

  // modifier method for losing health
  public void takeDamage (int amt) {
    health -= amt;
  }
}