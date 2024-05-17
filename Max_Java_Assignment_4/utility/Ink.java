package utility;

import java.util.Scanner;
import warrior.Warrior;
import weapon.Weapon;

public class Ink {

  public Ink() {
    // do nothing
  } // contructor

  // Welcome Message
  public void welcomeMessage() {
    System.out.println("""
      __        __             _             ____   ___ ____  _  _   
      \\ \\      / /_ _ _ __ ___(_)_ __ ___   |___ \\ / _ \\___ \\| || |  
       \\ \\ /\\ / / _` | '__/ __| | '_ ` _ \\    __) | | | |__) | || |_ 
        \\ V  V / (_| | |  \\__ \\ | | | | | |  / __/| |_| / __/|__   _|
         \\_/\\_/ \\__,_|_|  |___/_|_| |_| |_| |_____|\\___/_____|  |_|  
    """);
  }

  public boolean continueGame(Scanner input) {
    boolean isContinue = false;
    System.out.println("(1) continue game (2) new game");
    int choice = input.nextInt();
    if(choice == 1) {
      isContinue = !isContinue;
    }
    return isContinue;
  } // continueGame()

  // Warrior Menu
  public void printWarriorMenu() {
    String choice = "Select a character Type:";
    String space = "         ";
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
    System.out.printf("%-16s" + "%-26s\n", space, choice);
    System.out.printf("\n" + space + "1) Human" + space +  "2) Elf" + space +  "3) Orc\n");
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * * \n");
  }

  // Weapon Menu
  public void printWeaponMenu() {
    String space = "         ";
    String choice = "Select a Weapon:";
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("%-19s" + "%-26s\n", space, choice);
    System.out.printf("\n       1) Dagger" + space +  "2) Sword" + space + "3) Axe\n");
    System.out.printf("\n                         4) Staff");
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
  }
  
  // Armor Menu
  public void printArmourMenu() {
    String space = "         ";
    String choice = "Select an Armour:";
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("%-19s" + "%-26s\n", space, choice);
    System.out.printf("\n 1) Leather" + space +  "2) Chainmail" + space +  "3) Platemail\n");
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
  }

  // Attack Menu
  public int printAttackMenu(Scanner input) {
    int pick = 0;
    String space = "         ";
    String choice = "Choose Attack Type:";
    String surender = "\n             Or you prefer to surrender?\n";
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("%-18s" + "%-26s\n", space, choice);
    System.out.printf("\n   1) Normal" + space +  "2) Heavy" + space + "3) Save game\n");
    System.out.printf(surender);
    System.out.printf("\n                     4) Surrender");
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    pick = input.nextInt();
    return pick;
  }

  // Print Stats
  public void printStats(Warrior pWarrior, Warrior eWarrior, Weapon pWeapon, Weapon eWeapon) {
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("\n   Player: %-26s" + "Enemy: %-26s\n\n", pWarrior.getWarriorType(), eWarrior.getWarriorType());
    System.out.printf("   Weapon: %-26s" + "Weapon: %-25s\n", pWeapon.getWeaponType(), eWeapon.getWeaponType());
    System.out.printf("   Health: %-26d" + "Health: %-25d\n", pWarrior.getHealth(), eWarrior.getHealth());
    System.out.printf("   Strength: %-24d" + "Strength: %-25d\n", pWarrior.getStrength(), eWarrior.getStrength());
    System.out.printf("   Dexterity: %-23d" + "Dexterity: %-25d\n\n", pWarrior.getDexterity(), eWarrior.getDexterity());
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
  } // printStats()

  //Print win and loss count
  public void printWinLoss(Warrior player, Warrior enemy) {
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("   Player Wins: %-26d   Player Losses: %-26d\n", player.getWinCount(), player.getLooseCount());
    System.out.printf("   Enemy Wins: %-26d    Enemy Losses: %-26d\n", enemy.getWinCount(), enemy.getLooseCount());
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
}

  // GameOver Message
  public void printGameOver(String winner) {
    System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
    System.out.printf("""
         __ __
      .-',,^,,'.
     / \\(0)(0)/ \\
     )/( ,_"_,)\\(
     `  >-`~(   ' 
   _N\\ |(`\\ |___
   \\' |/ \\ \\/_-,)
    '.(  \\`\\_<
       \\ _\\|
        | |_\\_
        \\_,_>-'
    \nWinner: %s
        """, winner);
        System.out.println("\n * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
  } // printGameOver()

} // class