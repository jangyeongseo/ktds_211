package com.ktdsuniversity.edu.fp.basic.impl;

import com.ktdsuniversity.edu.fp.basic.CallSomething;

public class CallAge2 extends CallSomething {

	@Override
	public int call(String message) {
		try {
			return Integer.parseInt(message);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
