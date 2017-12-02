package com.fladev.utils;

import org.junit.Test;

import com.fladev.model.BsCall;
import com.fladev.model.CallMethod;

import junit.framework.TestCase;

public class JsonUtilsTest extends TestCase {

	@Test
	public void test_jsonTokenParsing() {
		BsCall call = new BsCall(CallMethod.POST);
		call.setUri(
				"http://api.betaseries.com/members/auth?key=046e30540ce7&v=3.0&login=Dev042&password=5e8edd851d2fdfbd7415232c67367cc3");
		String resp = CallExecUtils.executeCall(call);
		String token = JsonUtils.getAccessToken(resp);
		System.out.println(token);
		assertFalse(token.isEmpty());
	}
}
