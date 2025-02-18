package main;

import db.Util;

public class Main {

	public static void main(String[] args) {
		Util.init();
		new MainFrame("대한민국 축구 국가대표팀");
		System.out.println("Welcome to National Team Management");
	}
}
