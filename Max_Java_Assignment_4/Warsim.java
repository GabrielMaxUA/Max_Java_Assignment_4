import warrior.*;
import weapon.*;
import armour.*;
import weather.*;
import utility.*;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Random;

public class Warsim {

  // Objects
  public static Scanner input = new Scanner(System.in);
  public static Random randNum = new Random();
  public static Ink ink = new Ink();
  public static Weather weather;
  public static IO io = new IO();

  // Player Objects
  public static Warrior player; // player
  public static Weapon pWeapon; // player weapon
  public static Armour pArmour; // player armour

  // Enemy Objects
  public static Warrior enemy; // enemy
  public static Weapon eWeapon; // enemy weapon
  public static Armour eArmour; // enemy armour

  // Variables
  public static boolean gameOver = false;
  public static boolean playerTurn = true;
  public static int choice = 0;
  public static int attackType = 0;
  public static int damage = 0;
  public static String who = "Player";
  public static String winner = "";
  public static int eCount = 0;
  public static int pCount = 0;

  public static void main(String[] args) {
    ink.welcomeMessage();

    // set a random weather for the battle
    int weatherType = randNum.nextInt(4) + 1;
    createWeather(weatherType);
    
    String fileWinName = "gameWinSave.txt";
    File winFile = new File(fileWinName);

    String filePath = "./gameSave.txt"; // specify the file path
    File file = new File(filePath); // create the file
    
    // Check if the file exists
    if (file.exists()) {
      // if the file exists give them the option!
      // Prompt the player to either a) continue b) play new game
      boolean isContinue = ink.continueGame(input);
      if(isContinue) {
        // open save file, returns the newly created objects
        // we then assign those objects to our local object :)
        List<Object> things = io.loadGame(player, pWeapon, pArmour, enemy, eWeapon, eArmour);
        player = (Warrior)things.get(0);
        pWeapon = (Weapon)things.get(1);
        pArmour = (Armour)things.get(2);
        enemy = (Warrior)things.get(3);
        eWeapon = (Weapon)things.get(4);
        eArmour = (Armour)things.get(5);
        if(winFile.exists()){
          ink.printWinLoss(player, enemy);
        }
        else{
          System.out.println("Still no score. But let the games begin!!!");
        }
      }
      else {
        createGame(); // there is gameSave but they chose to start a new game
      }
    } // if
    else {
      createGame(); // no gameSave file start new game
    }
      
    ink.printStats(player, enemy, pWeapon, eWeapon);
    if(winFile.exists()){
      io.loadWinLoss(player, enemy);
     }
     else{
       System.out.println("Still no score. But let the games begin!!!");
     }
    
    // main game loop
    while(!gameOver) {
     // while the game is NOT over
      if(playerTurn) {
        System.out.println("Player's Turn");
        int choice = ink.printAttackMenu(input); // add a quit and save game option
        if(choice == 3) {
          io.saveGame(player, pWeapon, pArmour, enemy, eWeapon, eArmour);
          gameOver = !gameOver;
          break;
        }
        if(choice == 4){
          winner = "Enemy";
          player.addLooseCount();
          enemy.addWinCount();
          io.saveWinLoss(player, enemy);
          gameOver = !gameOver;
          break;
        }
        damage = pWeapon.strike(weather.getSeverity(), attackType, player.getStrength(), player.getDexterity());
        damage = eArmour.reduceDamage(damage);
        enemy.takeDamage(damage);
        if(!enemy.isAlive()) {
          winner = "Player";
          player.addWinCount();//adding win count to a winner
          enemy.addLooseCount();//adding loose count to an enemy
          io.saveWinLoss(player, enemy);//saving count to txt
          gameOver = !gameOver;
        }
      }
      else { 
        System.out.println("Enemy's Turn");// enemy turn code
        damage = eWeapon.strike(weather.getSeverity(), attackType = randNum.nextInt(2) + 1, enemy.getStrength(), enemy.getDexterity());
        damage = pArmour.reduceDamage(damage);
        player.takeDamage(damage);
        if(!player.isAlive()) {
          winner = "Enemy";
          player.addLooseCount();//adding loose count to a winner
          enemy.addWinCount();//adding win count to an enemy
          io.saveWinLoss(player, enemy);//saving count to txt
          gameOver = !gameOver;
        }
      }
      ink.printStats(player, enemy, pWeapon, eWeapon);
      playerTurn = !playerTurn; // toggle turns
    } // while()
    if(winFile.exists()){
      ink.printWinLoss(player, enemy);
    }
    else{
      System.out.println("Still no score. But let the games begin!!!");
    }
    ink.printGameOver(winner);
    if(winFile.exists()){
      ink.printWinLoss(player, enemy);
    }
    else{
      System.out.println("Still no score. But let the games begin!!!");
    }
  } // main()

  // Helper Methods
  public static void createWarrior(String who, int choice) {
    if(who.equals("Player")) {
      switch (choice) {
        case 1: // Human
          player = new Human("Human");
          break;
        case 2: // Elf
          player = new Elf("Elf");
          break;
        case 3: // Orc
          player = new Orc("Orc");
          break;
        default:
          System.out.println("oops!");
          break;
      } // switch
    }
    else {
      switch(choice) {
        case 1: // Human
          enemy = new Human("Human");
          break;
        case 2: // Elf
          enemy = new Elf("Elf");
          break;
        case 3: // Orc
          enemy = new Orc("Orc");
          break;
        default:
          System.out.println("oops!");
          break;
      } // switch
    }
  } // createWarrior()

  public static void createWeapon(String who, int choice) {
    switch(choice) {
      case 1: // Dagger
        if(who.equals("Player")) {
          pWeapon = new Dagger("Dagger");
        }
        else {
          eWeapon = new Dagger("Dagger");
        }
        break;
      case 2: // Sword
        if(who.equals("Player")) {
          pWeapon = new Sword("Sword");
        }
        else {
          eWeapon = new Sword("Sword");
        }
        break;
      case 3: // Axe
        if(who.equals("Player")) {
          pWeapon = new Axe("Axe");
        }
        else {
          eWeapon = new Axe("Axe");
        }
        break;
        case 4: // Staff
        if(who.equals("Player")) {
          pWeapon = new Staff("Staff");
        }
        else {
          eWeapon = new Staff("Staff");
        }
        break;
      default:
        System.out.println("oops!");
        break;
    } // switch
  } // createWeapon()

  public static void createArmour(String who, int choice) {
    switch(choice) {
      case 1: // Leather
        if(who.equals("Player")) {
          pArmour = new Leather("Leather");
        }
        else {
          eArmour = new Leather("Leather");
        }
        break;
      case 2: // Chainmail
        if(who.equals("Player")) {
          pArmour = new Chainmail("Chainmail");
        }
        else {
          eArmour = new Chainmail("Chainmail");
        }
        break;
      case 3: // Platemail
        if(who.equals("Player")) {
          pArmour = new Platemail("Platemail");
        }
        else {
          eArmour = new Platemail("Platemail");
        }
        break;
      default:
        System.out.println("oops!");
        break;
    } // switch
  } // createArmour()

  public static void createWeather(int weatherType) {
    switch (weatherType) {
      case 1: // sun 
        weather = new Sun();
        break;
      case 2: // rain
        weather = new Rain();
        break;
      case 3: // wind
        weather = new Wind();
        break;
      case 4: // storm
        weather = new Storm();
        break;
      default:
        System.out.println("Run!! Godzilla!!!");
        break;
    } // switch
  } // createWeather()

  public static void createGame() {
    //====================>>
    // Player Setup
    // Warrior
    ink.printWarriorMenu();
    int choice = input.nextInt(); // 1, 2 or 3
    createWarrior(who, choice);

    // Weapon
    ink.printWeaponMenu();
    choice = input.nextInt(); // 1, 2 or 3
    createWeapon(who, choice);

    // Armour
    ink.printArmourMenu();
    choice = input.nextInt(); // 1, 2 or 3
    createArmour(who, choice);

    who = "Enemy"; // swap the who with the what

    //====================>>
    // Enemy Setup
    // Warrior
    choice = randNum.nextInt(3) + 1;
    createWarrior(who, choice);

    // Weapon
    choice = randNum.nextInt(4) + 1;
    createWeapon(who, choice);

    // Armour
    choice = randNum.nextInt(3) + 1;
    createArmour(who, choice);
  } // createGame()

} // class