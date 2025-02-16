/// https://www.youtube.com/watch?v=kxPC6wEbbaU&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=3
package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class logDemo {
	
	@Test(priority=1)
	void testLogs()
	{
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.log().body() //this will print only response
			.log().cookies() ///this will print only cookies
			.log().headers() ///this will print only headers
			.log().all(); //this will the entire log in console window request, body, header, cookies, response
	}

}
