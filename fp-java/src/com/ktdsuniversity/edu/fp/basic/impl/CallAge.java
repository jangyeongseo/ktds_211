package com.ktdsuniversity.edu.fp.basic.impl;

import com.ktdsuniversity.edu.fp.basic.CallSomething;

public class CallAge extends CallSomething {

	@Override
	public int call(String message) {
		return Integer.parseInt(message);
	}

}
