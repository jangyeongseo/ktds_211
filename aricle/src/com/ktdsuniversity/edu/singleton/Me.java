package com.ktdsuniversity.edu.singleton;

/**
 * Singleton lnstance 
 */
public class Me {
	// 전역 => static
	private static Me me; // Singleton lnstance

	private String name;

	private Me() {
		this.name = "장영서";
	}

	public static Me getInstance() {
		if (Me.me == null) {
			Me.me = new Me();
		}
		return Me.me;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
