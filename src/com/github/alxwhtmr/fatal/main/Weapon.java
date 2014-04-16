package com.github.alxwhtmr.fatal.main;

import java.util.*;

import com.github.alxwhtmr.fatal.util.*;

public class Weapon {
	private String type;
	private String fatality;
	private String instrumental;
	private double speed;
	private int min;
	private int max;
	private ArrayList<String> swings;

	public Weapon(String type, String instrumental, 
		String fatality, double speed, 
		int min, int max, ArrayList<String> swings) {
		this.type = type;
		this.instrumental = instrumental;
		this.fatality = fatality;
		this.speed = speed;
		this.min = min;
		this.max = max;
		this.swings = swings;
	}

	public String getType() {
		return type;
	}

	public String getFatality() {
		return fatality;
	}

	public double getSpeed() {
		return speed;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public String getInstrumental() {
		return instrumental;
	}

	public ArrayList<String> getSwings() {
		return swings;
	}

	public String randomSwing() {
		return swings.get(new Random().nextInt(swings.size()));
	}
}