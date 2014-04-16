package com.github.alxwhtmr.fatal.main;

import com.github.alxwhtmr.fatal.util.*;
import java.util.*;

public class Member {
	private String 	nominativeName;
	private String 	accusativeName;
	private String 	dativeName;
	private double 	attackSpeed;
	private int 	hitPoints;
	private Weapon 	weapon;
	
	public Member(String nominative, String accusative, String dative, Weapon weapon, int hitPoints) {
		nominativeName = nominative;
		accusativeName = accusative;
		dativeName = dative;
		this.weapon = weapon;
		this.hitPoints = hitPoints;
	}

	public String getNominativeName() { 
		return nominativeName; 
	}

	public String getAccusativeName() {
		return accusativeName;
	}
	
	public String getDativeName() {
		return dativeName;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public int harm() {
		return (weapon.getMin() + new Random().nextInt(weapon.getMax() - weapon.getMin()));
	}

	public void getDamage(int dmg) {
		hitPoints -= dmg;
	}

	public boolean isDead() {
		return (hitPoints <= 0);
	}

	public void attack(Member m) {
    	for (int i = 0; i < Util.calculateAttacks(weapon.getSpeed()); i++) {
    		int dmg = harm();
    		m.getDamage(dmg);
    		System.out.println(Util.buildSentence(this, m, dmg));
    		if (m.isDead()) {
    			Util.delay(3);
    			System.out.println();
    			System.out.println(nominativeName + " " + weapon.getFatality() + " " + m.getDativeName() + "!");
    			System.out.println(m.getNominativeName() + " мертв!");
    			System.exit(0);
    		}
    	}
    }
}