package main;

import db.Util;

public class Main {

	public static void main(String[] args) {
		Util.init();
		new MainFrame("national team management");
		System.out.println("Welcome to National Team Management");
	}
}
