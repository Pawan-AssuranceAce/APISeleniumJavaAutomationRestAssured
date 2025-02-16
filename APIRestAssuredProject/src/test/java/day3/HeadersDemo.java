/// https://www.youtube.com/watch?v=kxPC6wEbbaU&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=3
package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class HeadersDemo {
	/// Header some value always remain the same that wont change like Server, Content type , expires, chache control and content encoding refer postman
	/// Other header will be keep changing like date, p3p, set-cookie, x-frame, x-xss
	/// Static we will validating mostly content type and content encoding
	
	 @Test (priority=1)
		 void testHeaders()
		 {
			given()
			.when()
				.get("https://www.google.com/")
			.then()
			
			/// get the header values for validation from postman testing request for google.com
				.header("Content-Type","text/html; charset=ISO-8859-1")
				.and() /// mandatory need to add and() but we can add and() when we have multiple validation
				.header("Content-Encoding", "gzip")
				.and()
				.header("server", "gws")
				.log().headers() //this will print only headers
				.log().all();
			
		 }
	 
		 @Test (priority=2)
		 void getHeaders() ///get the header details from response and save into variable and validate
		 {
			Response res=given()
							.when()
								.get("https://www.google.com/");
			
			// get single header info
			
			String headerValue=res.getHeader("Content-Type");
			/// Header is single key and value foreg: headername:value
			System.out.println("Header value of Content-Type====>"+headerValue);
			
			//get all headers info
			/// Headers is multiple  key and value foreg: headername:value headername:value headername:value headername:value
			Headers myheaders=res.getHeaders(); /// return type is Headers ..so variable type will be headers
			/// its not hashmap but still store the data in form of key and values
			
			for(Header hd:myheaders)
			{
				System.out.println(hd.getName()+"======="+hd.getValue());
			}
			///this feature is not that useful becoz .log().all() will print the all headers as part of response
		 }
	 
}
