package testPackage;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import  static io.restassured.RestAssured.*;

public class Test03_Post {

	@Test
	public void TestPost()
	{
		//Map<String, Object> map=new HashMap<String, Object>();
		
		//map.put("name","PJ");
		//map.put("job","Analyst");
		
		//JSONObject request=new JSONObject(map.toString());
		
		
		JSONObject request=new JSONObject();
		request.put("name","PJ");
		request.put("job","Analyst");
		System.out.println(request.toJSONString());
		
		given().
		    header("content-Type","application/json").
		    body(request.toJSONString()).
	    when().
	        post("https://reqres.in/api/users").
		then().statusCode(201).log().all();
		
		
		
		
	}
	
	@Test
	public void TestDelete()
	{
		
		when().delete("https://reqres.in/api/users/2").then().statusCode(204);
		
		
		
	}
}
