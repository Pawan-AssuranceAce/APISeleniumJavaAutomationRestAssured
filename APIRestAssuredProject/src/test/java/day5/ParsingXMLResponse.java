//// http://youtube.com/watch?v=IB3G7IbdD1k
package day5;


//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ParsingXMLResponse { //// traverse the XML response
	
	@Test(priority=1)
	void testXMLResponse()
	{
		/// Approach1 Validation with then() method with static values
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1") /// URL is not working use this for learning https://www.w3schools.com/xml/simple.xml
			
		.then()
			.statusCode(200)
			.header("Content-Type","apllication/xml") /// here the content will be xml instead of json
			.body("TravelerinformationResponse.page",equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelersinformation[0].name",equalTo("Vijay Barath Reddy")); ///first traveler information will be displayed
			
			
		///// Here writing xpath we are using dot . instead of slash / rest is same to write the as xpath
		//// XML reponse is easy to understand because all will be herarichy mode easy to understand
		//// Every line in xml called as node ...we are going to write xpath to reach the desired root node
		
		
		///// Approach 2 Validation with the response res variable saving the response in res then we will do validation
		
		Response res = given()
						
						.when()
							.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/html");
		//////JSON path is used in Json here similarly we are going to use xml path for body
		String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo,"1");
		
		String travelerName = res.xmlPath().getString("TravelerinformationResponse.travelers.Travelersinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Vijay Barath Reddy");

			
	}
	
	
	@Test(priority=2)
	void testXMLResponseBody()
	{
		Response res = given()
						
						.when()
							.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlobj= new XmlPath(res.asString()); /// asString() is types cast to res and toString() is used to convert input in actual string
		
		/// we are saving the all travelers information in the list so we do the validation
		List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelersinformation");
		Assert.assertEquals(travellers.size(),10);
		
		///Order of data deosnot matter we can validate in any order by using response res
		
		//Verify traveller name is present in response
		List<String> traveller_names=xmlobj.getList("TravelerinformationResponse.travelers.Travelersinformation.name");
		
			boolean status =false;
			for(String travellername :traveller_names)
			{
				System.out.println(travellername);
				if(travellername.equals("Vijay Barath Reddy"))
				{
					status=true;
					break;
				}
			}
			Assert.assertEquals(status, true);
	}
	
	

}
