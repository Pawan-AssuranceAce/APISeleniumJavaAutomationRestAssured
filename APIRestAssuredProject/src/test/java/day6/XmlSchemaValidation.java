//// https://www.youtube.com/watch?v=CyPsHcvl0vE&t=1730s

///In this we are going to validate the XML Schema

/// Note: Reponse and Schema are not same
//// Reponse Validation we will validate the data in the Reponse
//// Scheme Validation we will validate the Type of data (String,Number..so on)

//First we will capture the Response then we will validate schema type of data is same or not

/// Xml response is out dated techmonlogy now most of people use the Json response and
///Xml response we will get mostly with soap
/// Currently postman donot support the XML schema validation
/// we can do some validation on xml data in postman not sechma
//// Note: In Postman XML sechma validation is not possible there is workaround covert xml 
////       response into json and then into json sechma then validate but there is chance that 
////       not work to while creating sechma it uses the draft= 1 2 3 4 or 5 so it changes...
////       if that doesnt match then cant be validate..so xml sechma validation not possible in Postman
//// but in rest assured its possible


//// covert the xml response into xsd scehma  // https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
package day6;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XmlSchemaValidation {
	
	@Test
	void xmlSchemavalidation()
	{
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		.then() //validate
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("foodmenuSchema.xsd")); //// added the dummy file due to traveler api not working
		
		
	}

}
