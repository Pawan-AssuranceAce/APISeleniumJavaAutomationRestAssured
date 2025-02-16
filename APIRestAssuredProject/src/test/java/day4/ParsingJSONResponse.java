/// https://www.youtube.com/watch?v=5fWDqLFbJnA&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=4
package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ParsingJSONResponse { /// In this session we are going to learn the Validate the Response data from response Json.... prasing means travese though json get the required field or data
	///JSON is save the data as objects in store json we can see the under book is arrary inside array there is multiple book
	///details are given those saved as object inside the book array.
	/// JSON Object : what is inside the "{}" currly bracket is JSON object
	//// JSON Array :: What is inside the "[]" square bracket is JSON Array
	//// JSONObject---->JsonArray--->JsonObject: Thats means JSONObject have JSON array inside init and JSONarray have JSONobject in it..this is how response look like...what we used student.json
	//// JSONARRAY can have JSONObject and JSONOBJECT also have JSONArray both are valid
	
//	@Test (priority=1)
	void testJsonResponse()
	{
		//Approach 1 this approach works when we have small set of data... directly we can pass validation in the then() method
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.contentType("application/json")
			.body("book[3].title",equalTo("The Lord of the Rings")); /// we need take from this reponse or we can open the JSON path finder paste the response from postman(Launch the end url)
																/// from JSON path finder we can find the path for 3rd entry book title.
																//// we can write the multiple body methods to verify the data but last should be closed with semicolon ;
				
		
		/// Approach 2 ///In this when we have large data to validate then we will save the response in one variable and apply few methods to validate all data
						//// Approach 2 have more advantages
		
Response res = given() // we are going to save response in one variable res . Res will hold the whole response
				.contentType(ContentType.JSON)
		
		.when()
		   .get("http://localhost:3000/store");
		   
		/// to validate the res we need to use the testNG assertions
		/// when we get response in variable we can do more number of validation
		Assert.assertEquals(res.getStatusCode(),200); /// in this we are validating by using TestNG assert res status code
		Assert.assertEquals(res.header("Content-Type"),"application/json"); ///we can validate the header
		
		String bookname=res.jsonPath().get("book[3].title").toString(); /// we gave Jsonpath of res and found the book[3].title this we return the object so we added the tostring method
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
		//// in this approach we are not using then() becoz we are keeping the response res validation top of that... when dont have res response that time we need to add then() for validation
		/// then() we cannot write looping statement, conditional statement like if and we cant write asssert
		/// Validation order is not important .. we are validating from response so it can validate any order
	}
	
	@Test (priority=2)
	void testJsonResponseBodyData()
	{
	
Response res = given() 
				.contentType(ContentType.JSON)
		
				.when()
					.get("http://localhost:3000/store");
		   
		
				Assert.assertEquals(res.getStatusCode(),200); 
				Assert.assertEquals(res.header("Content-Type"),"application/json");
		
				String bookname=res.jsonPath().get("book[3].title").toString(); 
				Assert.assertEquals(bookname, "The Lord of the Rings");
				
				/// I have multiple books in json and each book objects have title of book.. so i want to capture the title of each book for each book object and print those title
				//// Note : if in json response the order of book got changed. Then it might be failed if we are passing index and validating.
				//// Here we are going to test the title of each book so we will store the all title of books and then vaildate one by one so here oder of books in response dont impact
				/// JSONObject class we are going to use
				
				JSONObject jo=new JSONObject(res.asString()); ///here we will convert the response res into Json object type...whole response will be treated as one object
				/// Response Json is object  and inside that book is array and book arr have different values 1 2 3 4 as different book
				
				for(int i=0; i<jo.getJSONArray("book").length();i++)
				{
					String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
					System.out.println(bookTitle);
				}
				
				//// now we will check particular title is present in json response or not
				
				boolean status = false;
				for(int i=0; i<jo.getJSONArray("book").length();i++)
				{
					String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
					if(bookTitle.equals("The Lord of the Rings"))
					{
						status = true;
						break;
					}
				}
				
				Assert.assertEquals(status,true);
				
				//// Find the price of all books and display
				
				double totalprice =0;
				
				for(int i=0;i<jo.getJSONArray("book").length();i++)
				{
					String bookPrice = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
					totalprice = totalprice+Double.parseDouble(bookPrice);
				}
				
				System.out.println(totalprice);
				Assert.assertEquals(totalprice, 526.0);
		
		}
	
	
}
