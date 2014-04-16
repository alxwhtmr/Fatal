package com.github.alxwhtmr.fatal.main;

import com.github.alxwhtmr.fatal.util.*;

import java.util.*;
import java.io.*;
import org.json.simple.*;

public class Init {
  Member m1, m2;
  ArrayList<Weapon> weaponsArrayList;
   
  public void init() throws IOException {
    initWeapons();
    initMembers();
    initFight();
  }

  private void initWeapons() {
    weaponsArrayList = Util.parseWeapons();
  }

  private void initMembers() {
    int weapons = weaponsArrayList.size(); 
    m1 = new Member("Вася", "Васю", "Васе", 
      weaponsArrayList.get(new Random().nextInt(weapons)), 1000);
    m2 = new Member("Николай", "Николая", "Николаю",
      weaponsArrayList.get(new Random().nextInt(weapons)), 1000);  
  }

  private void initRound(Member a, Member b) {
    Member first = a, second = b;
      int order = (new Random()).nextInt(2);
      if (order == 1) {
          first  = b;
          second = a;
      }
      first.attack(second);
      second.attack(first);
      System.out.println();
  }

  private void initFight() {
    while (true) {
      initRound(m1, m2);
      Util.delay(Constants.TICK);
    }
  }
}