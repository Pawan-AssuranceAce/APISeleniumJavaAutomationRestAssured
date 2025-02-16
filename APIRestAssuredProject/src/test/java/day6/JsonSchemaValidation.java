//// https://www.youtube.com/watch?v=CyPsHcvl0vE&t=1730s

///In this we are going to validate the Json Schema

/// Note: Reponse and Schema are not same
//// Reponse Validation we will validate the data in the Reponse
//// Scheme Validation we will validate the Type of data (String,Number..so on)

///First we will capture the Response then we will validate schema type of data is same or not
///https://jsonformatter.org/json-to-jsonschema ..... we can use this tool to convert the Json response and schema

package day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class JsonSchemaValidation {
	
	@Test
	void jsonschemavalidation()
	{
		given()
		.when()
			.get("http://localhost:3000/store") //sending the URL to get the response
		.then()
		/// here we need to do schema validation
		//// storeJsonSchema is we saved the schema into resourec folder of project ...we go from json to jschema tool
		/// matchesJsonSchemaInClasspath used to find the storeJsonSchema.json in the class path means in your project... if we change the location code not able to find the schema file location 
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
		
	}
	

}
