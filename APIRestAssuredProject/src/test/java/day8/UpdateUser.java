/// https://www.youtube.com/watch?v=3jO-Pkyrdo8
package day8;


//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void test_UpdateUser(ITestContext context) ///Now same we have to do here "id" to accessable from other class so we will add the Interface called ITestContext and varibale as context
	{
		///We will use the Faker to create the random data
		
				Faker faker = new Faker();
				JSONObject data = new JSONObject(); /// we need to create data ase JSON Object
				data.put("name",faker.name().fullName());
				data.put("gender","Male");
				data.put("email",faker.internet().emailAddress());
				data.put("status", "active"); ///we are updating this field
				
				String bearerToken ="=====AddTheBearerToken===="; // Need bearerToken
				
				int id= (int) context.getAttribute("user_id"); ///This id should come from the createuser class ...now we will get the id from other class
		//int id= (int) context.getSuite().getAttribute("user_id"); this method will get the attribute at suite level not on test level refer the testng.xml
					given() ///we need to get response for chaining 
					.header("Authorization","Bearer "+bearerToken)
					.contentType("application/json")
					.pathParam("id",id)
					.body(data.toString())
					
					///oder is not important we can write the body first and last pathparam
					
				.when()
					.put("htpps://gorest.con.in/public/v2/users/{id}") ///we need to send as PUT
				.then()
					.statusCode(200)
					.log().all();
	}

}
