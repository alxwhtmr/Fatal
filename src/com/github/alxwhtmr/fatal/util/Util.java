package com.github.alxwhtmr.fatal.util;

import com.github.alxwhtmr.fatal.main.*;
import java.util.*;
import java.io.*;
import org.json.simple.*;

public class Util {
	private static int count = 0;
	private static Random rand = new Random();

  public static String readFile(String file) throws IOException {
    Scanner s = null;
    String str = "";

    try {
      s = new Scanner(new InputStreamReader(new FileInputStream(file), "Cp1251"));
      while (s.hasNext()) {
        str += s.next();
      }
    } finally {
      if (s != null) {
        s.close();
      }
    }
    return str;
  }

	public static int calculateAttacks(double attackSpeed) {
    // Accessory variables
    int valToCompare  = (int) (attackSpeed * 100.0);
    int attacksCount  = 0;

    for (int i = 1; i <= Constants.TICK; i++) {  
      if (attackSpeed > 1.0 && attackSpeed < 2.0) {
        ++attacksCount;
        valToCompare = (int) ((1.0 - (2.0 - attackSpeed)) * 100);
      } else if (attackSpeed > 2.0 && attackSpeed < 3.0) {
        attacksCount += 2;
        valToCompare = (int) ((1.0 - (3.0 - attackSpeed)) * 100);
      }
      if (isAttackIncremented(valToCompare)) {
        ++attacksCount;
      }
    }

    return attacksCount;
  }

  public static boolean isAttackIncremented(int valToCompare) {
		// The bigger 'valToCompare' is, the more chances to increment attack
    // For example 1.92 will produce 2 attacks very often
    int randomDice = rand.nextInt(Constants.FULL_CHANCE) + 1;
		if (randomDice < valToCompare) {
			return true;
		}
		return false;
	}

  public static ArrayList<Weapon> parseWeapons() {
    String weaponsString = "";
    ArrayList<Weapon> weaponsArrayList = new ArrayList<Weapon>();
    
    try {
      weaponsString = Util.readFile(Constants.WEAPONS_FILE);
    } catch(IOException e) {
      System.err.println("Caught IOException: " + e.getMessage());
    }

    Object weaponsObj = JSONValue.parse(weaponsString);
    JSONArray weaponsArray = (JSONArray)weaponsObj;

    for (int i = 0; i < weaponsArray.size(); i++) {
      JSONObject weaponJsonObj = (JSONObject)weaponsArray.get(i);
      String type = weaponJsonObj.get("type").toString();
      String instrumental = weaponJsonObj.get("instrumental").toString();
      String fatality = weaponJsonObj.get("fatality").toString().replaceAll("_", " ");
      int min = Integer.parseInt(weaponJsonObj.get("min").toString()); 
      int max = Integer.parseInt(weaponJsonObj.get("max").toString());
      double speed = Double.parseDouble(weaponJsonObj.get("speed").toString());
      String swingString = weaponJsonObj.get("swing").toString().replaceAll("[\\[\\]\"]","");
      String[] swingArr = swingString.split(",");
      
      ArrayList<String> swingArrayList = new ArrayList<String>();
      for (String swing : swingArr) {
        swingArrayList.add(swing);
      }
      weaponsArrayList.add(new Weapon(type, instrumental, fatality, speed, min, max, swingArrayList));
    }
    return weaponsArrayList;
  }

	public static void delay(int s) {
    try {
        Thread.sleep(1000 * s);
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }  
  }

  public static String randomPreffix() {
    String[] pref = {"Замахнувшись, ", "В ярости, ", "Разъяренный ",
    "Обезумевший ", "Поехавший "};
    return pref[new Random().nextInt(pref.length)];
  }

  public static String buildSentence(Member at, Member op, int dmg) {
    String str = "";
    switch (new Random().nextInt(4)) {
      case 0:
        str = at.getNominativeName() + " " + at.getWeapon().randomSwing() + 
        " " + op.getAccusativeName();
        break;
      case 1:
        str = randomPreffix() + at.getNominativeName() + " " + at.getWeapon().randomSwing() + 
        " " + op.getAccusativeName() + " " + at.getWeapon().getInstrumental();
        break;
      case 2:
        str = at.getNominativeName() + " " + at.getWeapon().randomSwing() + 
        " " + op.getAccusativeName() + " " + at.getWeapon().getInstrumental();
        break;
      case 3:
        str = randomPreffix() + at.getNominativeName() + " " + at.getWeapon().randomSwing() + 
        " " + op.getAccusativeName();
        break;
      default:
        break;
    }
    return str;
  }
} 
