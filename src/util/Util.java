package util;

import java.util.Scanner;

public class Util {
	private static Scanner sc = new Scanner(System.in);
	
	public static int getValue(String msg, int i, int j) {
		System.out.println(msg + "[" + i + "-" + j + "]");
		while(true) {
		try {
			int value = sc.nextInt();
			if(value < i || value > j) {
				System.out.println(i+" ~ " + j +" 사이의 값 입력");
				continue;
			}
			return value;
		} catch (Exception e) {
			System.out.println("숫자 값 입력");
			sc.nextLine();
		}
		}
	}

	public static String getValue(String msg) {
		System.out.println(msg +" 입력 : ");
		String value = sc.next();
		sc.nextLine();
		// TODO Auto-generated method stub
		return value;
	}

}
