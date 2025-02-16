/// https://www.youtube.com/watch?v=kxPC6wEbbaU&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=3
package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	// @Test (priority=1)
	 void testCookies()
	 {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
		
		/// now we want to vaildate the some cookies and cookies value is dymanic so we will check the cookies name that we can get from postman using same request URL
		/// inside cookie method we need to give the name of cookie which is AEC and pass the value of which is not gonna same both we can get from Post man
		///If our cookie test is failed that means it working as expected becoz every time cookie value should not be same and it should not match with hard coded value
			.cookie("AEC","AVcja2eJSGjJGHYikfksfgebewDFgdsdT5wqy4SqrVVI84vLjMzAbF_8aQctg6z-AGo")
			.log().all();
		
	 }
	
	
	@Test (priority=2)
	 void getCookiesInfo()
	 {
		Response res=given() 
		.when()
			.get("https://www.google.com/");
			/// Here we will try to get whole response as variable 
				/// is "res" we will get the all info like response , cookies , header, request but we want cookies alone
		///write java code to get cookies from "res" variable
		
		//get Single cookie info and name of cookie wont change
		String cookie_value=res.getCookie("AEC");
		System.out.println("Value of cookie is ====>"+cookie_value);
		
		
		/// get all cookie info
	Map<String,String>	cookies_values=res.getCookies();
	
	///How to read only keys form cookies_values
	 System.out.println(cookies_values.keySet());
	
	/// for each loop to read each key and values
	
	for(String k:cookies_values.keySet())
	{
		String cookie_values = res.getCookie(k);
		System.out.println(k+"======"+cookie_values);
		
	}
		
	 }

}
