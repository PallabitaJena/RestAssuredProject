package testPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import  static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;
public class Test02_GETStatic 
{
	//@Test(priority=1)
	public  void Test02()
	{
		given()
		    .get("https://reqres.in/api/users?page=2")
		.then()
		     .statusCode(200)
		     .body("data.id[0]", equalTo(7))
	         .body("data.first_name",hasItems("Michael","Lindsay"))
	         .log().body();
	         //.extract().path("data");
	}
	
	
	/*
	 * @Test(priority=2) public void Test03() { //Response
	 * response=get("https://reqres.in/api/users?page=2"); JSONArray
	 * jsonArray=given().get("https://reqres.in/api/users?page=2").then().statusCode
	 * (200).extract().path("data"); for(int i=0; i<jsonArray.length(); i++) {
	 * JSONObject item = jsonArray.getJSONObject(i); //gets the ith Json object
	 * ofJSONArray String customerId = item.getString("id");
	 * System.out.println(customerId);
	 * 
	 * } }
	 */
	  
		
		//  @Test(priority=2) 
	public void Test04() throws UnsupportedEncodingException, ParseException
		  { 
			  //Response response=get("https://reqres.in/api/users?page=2"); 
		  
			/*  JSONParser parser = new JSONParser();
				Object obj  = parser.parse(content);
				 jsonArray.add(obj);*/
			 JSONParser parser = new JSONParser();
			  Response response=get("https://reqres.in/api/users?page=2");
		      Object obj = parser.parse( response.asInputStream());
	    	  
		System.out.println("ResultList.toString:"+ response.getBody().toString());	  
		org.json.JSONObject obj1 = new JSONObject(obj.toString());
	
		//org.json.JSONObject obj1 = new JSONObject(response.asString());
		System.out.println("Added to jsonarray");
		org.json.JSONArray jsonArray = obj1.getJSONArray("data");
		
		System.out.println("For loop");
		for(int i=0;i<jsonArray.length();i++)
		{
            System.out.println("array is " + jsonArray.get(i));
            System.out.println("Id is " + jsonArray.getJSONObject(i).getInt("id"));

        }
		 
	  
	  }
		  
		  @Test
		  public void dynamicParse() throws UnsupportedEncodingException, ParseException
		  {
			  JSONParser parser=new JSONParser();
			  Response response=get("https://reqres.in/api/users?page=2");
			  
			  Object obj=parser.parse(response.asInputStream());
			  
			  JSONObject jo=new JSONObject(obj.toString());
			  
			  getKeysValue(jo,"first_name");
		  }


		public void getKeysValue(JSONObject jo, String key) {
			boolean exists=jo.has(key);
			
			Iterator<?> keys;
			String nextkeys;
			if(!exists)
			{
				keys=jo.keys();
				while(keys.hasNext())
					
				{
					//System.out.println("inside while block");
					nextkeys=(String)keys.next();
					//System.out.println("the nexytkeys is : "+nextkeys);
					try 
					{
						//System.out.println("inside try block");
						if(jo.get(nextkeys) instanceof JSONObject)
						{
							//System.out.println("inside fist if block");
							if(exists==false)
							{
								getKeysValue(jo.getJSONObject(nextkeys), key);
							}
							else
							{
								System.out.println("For the key value :"+key+" the value we got :"+jo.get(key));
							}
						}
						else if(jo.get(nextkeys) instanceof JSONArray)
						{
							//System.out.println("inside elseif block");
							JSONArray jsonarray=jo.getJSONArray(nextkeys);
							for(int i=0;i<jsonarray.length();i++)
							{
								String jsonarraystring=jsonarray.get(i).toString();
								JSONObject innerjson=new JSONObject(jsonarraystring);
								if(exists==false)
								{
									getKeysValue(innerjson, key);
								}
								else
								{
									System.out.println("For the key value :"+key+" the value we got :"+jo.get(key));
								}
							}
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			else
			{
				System.out.println("For the key value :"+key+" the value we got :"+jo.get(key));
			}
			
		}
	 
	

}
