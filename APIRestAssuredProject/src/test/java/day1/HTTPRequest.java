//// https://www.youtube.com/watch?v=OM4mr3PKgcQ&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=2
/////API automatomation referral site: https://reqres.in/

package day1;

import org.testng.annotations.Test;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class HTTPRequest {
	int id;
	
	@Test(priority=1)
	void getUser()
	{
		given() //Sometimes given optional if u dont have anything for given. Directly write when and then
		
		
		//Lets if we dont have given then no dot for when so which method start first need not dot.
		.when() //Becoz comes under given need to put dot
			.get("https://reqres.in/api/users?page=2") //it will return mutiple user
		.then() //Becoz comes under given need to put dot
			.statusCode(200) //Here verify the status code from response
			.body("page",equalTo(2))  //validate from response not need to mention json path directly we use like this beoz its 1st parameter in response
			.log().all(); //it will display the response in the console ";" becoz this last step
	}
	
	@Test(priority=2)
	void createUser()
	{
		//We need the Test data in response so we will create HashMap for passing data into Json and Json into request body
		//but in real time HashMap is not suggested due to hard coding should be avoided.
		//// https://reqres.in/
		HashMap data = new HashMap();
		data.put("name","Aman");
		data.put("job","Engineer"); 
		
		id=given() // HERE WE ARE return id to golbal variable from the response
			.contentType("application/json") //we can given any name
			.body(data) //it refer map data sending in form json here
						//Request is completed
		.when()
			.post("https://reqres.in/api/users") //added the End url
			.jsonPath().getInt("id"); //here we want to save the response data for id in golbal variable id; so commmented then() method
		
	//	.then()
		//	.statusCode(201) //when you create something 201 code we will get
		//	.log().all();
	}
	
	@Test(priority=3,dependsOnMethods= {"createUser"}) //dependsOnMethods used to check if that createUser() is passed then only UpdateUser() will execute otherwise it will skip it
	void updateUser()
	{
				HashMap data = new HashMap();
				data.put("name","Gupta");
				data.put("job","Doctor"); //want to update this data 
				
				given()
					.contentType("application/json") 
					.body(data) 
					
					
				.when()
					.put("https://reqres.in/api/users/"+id) //but here we need to pass the id here which we generated in second in request void createUser()
				
					.then()
						.statusCode(200) //same statuscode for updating
						.log().all();
		
	}
	
	@Test (priority=4)
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204) //while delete we will get 204
			.log().all();
	}

}
