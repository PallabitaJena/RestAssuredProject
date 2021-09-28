package testPackage;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class DataDrivenExample {
	
	
	public  void parsing() throws UnsupportedEncodingException, ParseException {
	JSONParser parser= new JSONParser();
	//change
	Response response =RestAssured.get("https://reqres.in/api/users?page=2");
	
	Object obj=parser.parse(response.asInputStream());
	
	//typecasting object into jsonObject
	JSONObject json= new JSONObject(obj.toString());
	getkey(json,"name");
	
	}
	
	public static void getkey(JSONObject json,String key)
	{
		boolean exists=json.has(key);
		
		if(!exists)
		{
			Iterator<?> keys;
			String nextkeys;
			
			keys=json.keys();
			
			while(keys.hasNext())
			{
				nextkeys=(String)(keys.next());
				try
				{
					if(json.get(nextkeys) instanceof JSONObject)
					{
						exists=json.has(key);
						if(exists==false)
						{
							getkey(json.getJSONObject(nextkeys),key);
						}
						
					}
					else if(json.get(nextkeys) instanceof JSONArray)
					{
						JSONArray jsonarray=json.getJSONArray(nextkeys);
						for(int i=0;i<jsonarray.length();i++)
						{
							String jsonstring=jsonarray.get(i).toString();
							JSONObject innerobject=new JSONObject(jsonstring);
							exists=innerobject.has(key);
							if(exists==false)
							{
								getkey(innerobject,key);
							}
						}
						
							
						
					}
					else
					{
						System.out.println(json.get(key));
					}
				}
				catch(Exception e)
				{
					
				}
			}
		}
		else
		{
			System.out.println(json.get(key));
		}
	}
}
