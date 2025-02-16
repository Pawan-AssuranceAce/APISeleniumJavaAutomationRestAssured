/// https://www.youtube.com/watch?v=3jO-Pkyrdo8
package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class CreateUser {

	@Test
	void test_CreateUser(ITestContext context) ///we have make the "id" to accessable other class so we will add the Interface called ITestContext and varibale as context
	{
		///We will use the Faker to create the random data
		
		Faker faker = new Faker();
		JSONObject data = new JSONObject(); /// we need to create data ase JSON Object
		data.put("name",faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken ="=====AddTheBearerToken===="; // Need bearerToken
		
	int id=given() ///we need to get response for chaining 
			.header("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("htpps://gorest.con.in/public/v2/users")
			.jsonPath().getInt("id"); /// no need to capture the complete response we need only id for to use as request for another API
			
	
	System.out.println("Generated id is:"+id);
	
	//Here making "id" variable for available for other classes
	context.setAttribute("user_id", id); ///this method will set attribute test level whatever classes run under that test level can access the attribute refer the Testng2.xml
///	context.getSuite().setAttribute("user_id", id)  this method will set the attribute at suite level not on test level refer the testng.xml
	}
}
