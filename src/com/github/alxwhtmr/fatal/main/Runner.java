package com.github.alxwhtmr.fatal.main;

import java.io.*;

public class Runner {
	private static void run() throws IOException {
		(new Init()).init();	
	}

	public static void main(String[] args) {
		try {
			run();
		} catch(IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
}