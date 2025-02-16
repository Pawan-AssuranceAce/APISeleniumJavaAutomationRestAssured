//// https://www.youtube.com/watch?v=xIidl6Iua0o&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=3

package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


/*
 Different ways to Create POST RequestBody
 1. Post Request Body using HashMap.
 2. Post Request Body Creation using org.json
 3. Post Request Body Creation using POJO (Plain old java Object) class
 4. Post Request Body using External Json file data.
 //// C:\yourfolderpath\automationPractice\jsonfiles       ///Kept own API file as student 
  //// to run own server open cmd from same folder path now type "json-server students.json" press enter
 */

public class DiffWaysToCreatePostRequestBody {

	
	//1. Post Request Body using HashMap.
	
//	@Test(priority=1)
	void testPostUsingHashMap()
	{
		HashMap data = new HashMap();
		data.put("name", "scott");
		data.put("location", "france");
		data.put("phone", "123456");
		
		//now course is given in from of array but course is type of key and its have value but those value are form of array
		String courseArr[] = {"C","C++"};
		data.put("course",courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students") /// to get this we need to  run the Local API server in cmd(students.json path file to open the cmd) "json-server students.json"

		.then()
			.statusCode(201)
			.body("name",equalTo("scott"))
			.body("location",equalTo("france"))
			.body("phone",equalTo("123456"))
			.body("course[0]",equalTo("C"))
			.body("course[1]",equalTo("C++"))
			.header("Content-Type","application/json")
			.log().all();
	}
	
	
	/// 2. Post Request Body Creation using org.json lib
	
	///	@Test(priority=1)
		void testPostUsingJsonLib() /// to implement this we need to add the Json org dependency in POM XML
		{
			/// we have create Json Object 
			
			JSONObject data = new JSONObject();
			//// similar hashMap we can add the test data in the JSONObject data
			
			data.put("name","Scott");
			data.put("location","France");
			data.put("phone","123456");
			data.put("name","Scott");
			
			//now course is given in from of array but course is type of key and its have value but those value are form of array
					String courseArr[] = {"C","C++"};
					data.put("course",courseArr);
			
			given()
				.contentType("application/json")
				.body(data.toString()) ///Json object data we cannot send the direct we need to convert in to string then we can pass
			
			.when()
				.post("http://localhost:3000/students") /// to get this we need to  run the Local API server in cmd(students.json path file to open the cmd) "json-server students.json"

			.then()
				.statusCode(201)
				.body("name",equalTo("Scott"))
				.body("location",equalTo("France"))
				.body("phone",equalTo("123456"))
				.body("course[0]",equalTo("C"))
				.body("course[1]",equalTo("C++"))
				.header("Content-Type","application/json")
				.log().all();
		}
		
		
		/// 3. Post Request Body Creation using Pojo class
		
		//	@Test(priority=1)
			void testPostUsingPOJO() 
			{
				///Create Pojo class(encapsulations) under same package use here.. now create object to access the Pojo class
				Pojo_PostRequest data = new Pojo_PostRequest();
				data.setName("Scott");
				data.setLocation("france");
				data.setPhone("1245689");
				String courseArr[] = {"C","C++"};
				data.setCourses(courseArr);
				
				
				given()
					.contentType("application/json")
					.body(data) //To string is not needed here its required in only Json lib
				
				.when()
					.post("http://localhost:3000/students") /// to get this we need to  run the Local API server in cmd(students.json path file to open the cmd) "json-server students.json"

				.then()
					.statusCode(201)
					.body("name",equalTo("Scott"))
					.body("location",equalTo("france"))
					.body("phone",equalTo("1245689"))
					.body("courses[0]",equalTo("C"))
					.body("courses[1]",equalTo("C++"))
					.header("Content-Type","application/json")
					.log().all();
			}
			
			/// 4. Post Request Body Creation using External Json file data.
			
			@Test(priority=1)
			void testPostUsingExternalJsonFile() throws FileNotFoundException 
			{
				///Created external json file under project folder body.json
				/// Step1 we need to open the body.json file
				File f = new File(".\\body.json");
				/// Step2 read the body.json file
				FileReader fr = new FileReader(f);
				/// Step 3 we need to get the body.json data in Json formatter
				JSONTokener jt = new JSONTokener(fr);
				////In second approach we used the JSONObject
				JSONObject data = new JSONObject(jt);
				
				
				given()
					.contentType("application/json")
					.body(data.toString()) //To string is needed here its required becoz data coming from Json object
				
				.when()
					.post("http://localhost:3000/students") /// to get this we need to  run the Local API server in cmd(students.json path file to open the cmd) "json-server students.json"

				.then()
					.statusCode(201)
					.body("name",equalTo("Scott"))
					.body("location",equalTo("france"))
					.body("phone",equalTo("1245689"))
					.body("courses[0]",equalTo("C"))
					.body("courses[1]",equalTo("C++"))
					.header("Content-Type","application/json")
					.log().all();
			}
	
	@Test(priority=2)
	void testDelete()
	{
		given()
		
		.when()
			.delete("http://localhost:3000/students/309d")
		
		.then()
			.statusCode(200);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
