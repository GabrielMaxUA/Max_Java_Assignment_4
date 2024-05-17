package warrior;

import java.util.Random;

// abstract means it CANNOT be directly instantiated!
public abstract class Warrior {
  // public(open to all), private(internal to class)
  // protected(closed outside, open on the inside)
  protected Random randNum = new Random();
  private int health;
  private int strength;
  private int dexterity;
  private String warriorType;
  private int winCount;
  private int looseCount;

  public Warrior() {
    // do nothing
  } // contructor

  // called when we are reloading the save game
  public Warrior(int health, int strength, int dexterity, String warriorType) {
    this.health = health;
    this.strength = strength;
    this.dexterity = dexterity;
    this.warriorType = warriorType;
  } // Warrior()

  //===============>>
  // GETTERS
  public int getHealth() {
    return this.health;
  }
  public int getStrength() {
    return this.strength;
  }
  public int getDexterity() {
    return this.dexterity;
  }
  public String getWarriorType() {
    return this.warriorType;
  }

  public int getWinCount() {
    return this.winCount;
  }

  public int getLooseCount() {
    return this.looseCount;
  }
  public boolean isAlive() {
    if(this.health > 0)
      return true;
    else 
      return false;
  }

  //===============>>
  // SETTERS
  public void setHealth(int health) {
    this.health = health;
  }
  public void setStrength(int strength) {
    this.strength = strength;
  }
  public void setDexterity(int dexterity) {
    this.dexterity = dexterity;
  }
  public void setWarriorType(String warriorType) {
    this.warriorType = warriorType;
  }

  public void setWinCount(int winCount) {
    this.winCount = winCount;
  }

  public void setLooseCount(int looseCount) {
    this.looseCount = looseCount;
  }

  public void addWinCount() {
    this.winCount++;
}

public void addLooseCount() {
    this.looseCount++;
}
  public void takeDamage(int damageAmount) {
    if(damageAmount > 0)
      this.health -= damageAmount;
  }
} // class
