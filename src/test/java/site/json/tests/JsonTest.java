package site.json.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import site.json.actions.Json;

public class JsonTest {
	@Test
	public void test() {
		Json json = new Json();

		Assert.assertTrue(json.isJSONValid());

		Assert.assertTrue(json.checkNodeExists("title"));

		Assert.assertTrue(json.getNodeValue("title").equals("delectus aut autem"));

		boolean convertedNodeValue = json.getConvertedNodeValue("completed");

		Assert.assertNotNull(convertedNodeValue);
	}
}
