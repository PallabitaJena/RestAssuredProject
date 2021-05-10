
package testPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test01_GET {
	@Test
	public void testo1()
	{
		
		Response response=RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());		
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getHeader("content-type"));
		
		int statuscode =response.statusCode();
		Assert.assertEquals(statuscode, 200);
	}

}
