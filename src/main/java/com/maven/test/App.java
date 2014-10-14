package com.maven.test;

import java.net.URL;

public class App {
	public static void main(String[] args) {
		System.out.println("---this is mvn----");
		URL path = App.class.getResource("");
		URL path1 = App.class.getResource(".");
		System.out.println("---path--->" + path);
        System.out.println("---path1--->" + path1);
	}
}
