/// https://www.youtube.com/watch?v=3jO-Pkyrdo8
package day8;


//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void test_getUser(ITestContext context) ///Now same we have to do here "id" to accessable from other class so we will add the Interface called ITestContext and varibale as context
	{
		int id= (int) context.getAttribute("user_id"); ///This id should come from the createuser class ...now we will get the id from other class
		//int id= (int) context.getSuite().getAttribute("user_id"); this method will get the attribute at suite level not on test level refer the testng.xml
		
		String bearerToken="=====AddTheBearerToken====";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id",id)
		.when()
			.get("htpps://gorest.con.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
