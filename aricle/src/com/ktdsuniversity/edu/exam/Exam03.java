package com.ktdsuniversity.edu.exam;

public class Exam03 {
	private String ascii;

	public Exam03() {
		this.ascii = "A";
	}

	public void start() {
		System.out.println("입력으로 주어진 글자의 아스키 코드 값을 출력한다.");

		char a = this.ascii.charAt(0);
		int asciiCode = (int) a; // char 를 int 로 변환
		System.out.println(asciiCode);

	}

	public static void main(String[] args) {
		// Q11654
		Exam03 exam03 = new Exam03();
		exam03.start();

	}

//	  public int check(String s) {
//		    return (int) s.charAt(0);
//			혹은 return s.charAt(0) + 0; 을 하면 int형으로 변환
//		  }
//
//		  public static void main(String[] args) {
//		    Q11654 check = new Q11654();
//		    System.out.println(check.check("A"));
//		    System.out.println(check.check("C"));
//		    System.out.println(check.check("0"));
//		    System.out.println(check.check("9"));
//		    System.out.println(check.check("a"));
//		    System.out.println(check.check("AB"));
//		  }

}
