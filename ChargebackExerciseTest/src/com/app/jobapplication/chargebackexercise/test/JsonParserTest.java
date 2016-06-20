package com.app.jobapplication.chargebackexercise.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.app.jobapplication.chargebackexercise.JsonParser;
import com.app.jobapplication.helper.StringPair;

public class JsonParserTest {

	JSONObject obj;
	@Before
	public void setUp() throws Exception {
		obj = new JSONObject();
		obj.put("name", "John");
		obj.put("href", "testurl.com");
	}

	@Test
	public void test() {
		Map<String, StringPair> map = JsonParser.parse(obj);
		assertEquals(map.get("name").getValue(),"John" );
		assertEquals(map.get("href").getValue(),"testurl.com");
	}

}
