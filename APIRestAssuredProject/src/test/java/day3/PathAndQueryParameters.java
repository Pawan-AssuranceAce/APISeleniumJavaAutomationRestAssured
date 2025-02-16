/// https://www.youtube.com/watch?v=kxPC6wEbbaU&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=3

package day3;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	/// https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testQueryAndPathParameters()
	{
		given()
			.pathParam("mypath","users") /// Path Parameter /// If u have mutiple path parameter we need to create seperate path parameters same with query parameter.
			.queryParam("page",2) /// Query Parameter
			.queryParam("id", 5) //// Query Parameter
			//// Advantage instead of hard coding the values we can give as parameter we can change here in query parameter instead of changing in the URL itself 
		
		.when()
			.get("https://reqres.in/api/{mypath}") /// only path parameter need to mention here and query parameter will go along the request that no need to send separately
													/// Users is end point so we defined in parameter and till api/ is common for every request so we not taken api as path param
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
