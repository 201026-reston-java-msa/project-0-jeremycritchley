package com.utils;

import java.util.Scanner;

public class ScannerUtil {
	private static Scanner scan;
	public static Scanner getScanner() {
		if (scan == null) {
			scan = new Scanner(System.in);
		}
		
		return scan;
		
	}
}
