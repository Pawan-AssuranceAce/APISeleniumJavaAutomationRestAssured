/// https://www.youtube.com/watch?v=SBp5rStA3vQ
package day7;

import org.testng.annotations.Test;
//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Authentications {
	//// Note: There is 2 header ... 1 is Request Header and 2 is Response Header we will get during response
	///// Authentications : User is valid or not that means user have right cerdentials(Username and Pass)
	///// Authorization : For That Valid user it will check the access or permission. Only Authenticated user can have the Authorization
	///// Type of Authentications supported by Rest assured
	////  1. Basic Authentications
	///// 2. Digest Authentications
	////  3. Preemptive Authentications
	///// 1,2 and 3 Authentications are almost similar they use the Username and Password ...Internally they worked differently
	///// 4. Bearer Token Authentications : we have to create own token
	////  5. oauth 1.0 and oauth 2.0 Authentications //// oauth 1.0 is almost depcricated /// oauth used when API is very complex /senstive/critical and need the lot of verification to access... its difficult to break this Oauth Authentications
	///// 6. API Keys Authentications
	
////Postman and Rest Assured both dont support the same Authentications...but some common are there
//// Accroding to API design we need to use the Authentications. When we will get the swagger there it will tell you need token or API key or user
	
////1. Basic Authentications: we have just user name and password
	
	@Test(priority=1)
	void testBasicAuthentications()
	{
		////// In Postman its given
		given()
			.auth().basic("postman", "password") /// directly username and Password goes to server and allocated
		.when()
			.get("https://postman-echo.com/basic-auth")	
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}

	
	///// 2. Digest Authentications
	@Test(priority=2)
	void testDigestAuthentications()
	{
		given()
			.auth().digest("postman", "password") /// Internal algorithm is different..it will hit/check the multiple times server
		.when()
			.get("https://postman-echo.com/basic-auth")	////same it will support the digest,basic and Preemptive Authentications
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
	///Note: Email uses the Digest or Preemptive Authentications to validate the username and passwords
	
////3. Preemptive Authentications : its combination of Basic and digest
	@Test(priority=3)
	void testPreemptiveAuthentications()
	{
		given()
			.auth().preemptive().basic("postman", "password") /// Internal algorithm is different..it will hit/check the multiple times server
		.when()
			.get("https://postman-echo.com/basic-auth")	////same it will support the digest Authentications
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
///// 4.Bearer Token Authentications : this also very common or famous...and its given in postman too
	@Test(priority=4)
	void bearerTokenAuthentications()
	{
		String bearerToken ="ghp_kSWVnhJhGHVz6QqNsdsfsdft14jhgfufutA8rM4CCHf7"; //// Token will change application to applications
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
////5. oauth 1.0 Authentications : parameter required ("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate")
	
	@Test(priority=5)
	void testOauth1Authentications()
	{
		given()
			.auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate") ///This Oauth 1.0 Authentications..developer will give details to genrate the Oauth1 ("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate") //// Many application follows the Oauth 2 Authentications because its advance
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	
	
////5. oauth 2.0 Authentications : 	Only One Parameter required
	@Test(priority=6)
	void testOauth2Authentications()
	{
		
		given()
			.auth().oauth2("ghp_kSWVnhJhGHVz6QqNsdsfsdft14jhgfufutA8rM4CCHf7") ////we are passing the Oauth
		
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}




///// 6. API Keys Authentications

	@Test(priority=7)
	void testAPIAuthentications()
	{
		/// Method1
		given()
			.queryParam("appid", "qNst1SDagfg4ORmfdgdgtdfgKA8rM4CCHf7") /// Here are setting appid as APIKey and Value as Key value in Query param
		.when()
			.get("https://openweathermap.org/data/2.5/forecast/daily?q=Delhi&Unit=metric&cnt=7")
			//// https://openweathermap.org ....till is here domain path (host)..... 
			///// data/2.5/forecast/daily ....path parameter
			///// q=Delhi&Unit=metric&cnt=7 ....Query Parameter
		.then()
			.statusCode(200)
			.log().all();
		
		/// Method2 instead of giving domain path, path parameter and query parameter in same we will give separate in method 2
				given()
					.queryParam("appid", "qNst1SDagfg4ORmfdgdgtdfgKA8rM4CCHf7") /// Here are setting appid as APIKey and Value as Key value in Query param
					.pathParam("mypath","data/2.5/forecast/daily")
					.queryParam("q","Delhi")
					.queryParam("Unit","metric")
					.queryParam("cnt","7")
				///// data/2.5/forecast/daily ....path parameter
					///// q=Delhi&Unit=metric&cnt=7 ....Query Parameter
					
				.when()
					.get("https://openweathermap.org/{mypath}")
					//// https://openweathermap.org ....till is here domain path (host)..... 
					
				.then()
					.statusCode(200)
					.log().all();
				
				
				///During time of development developer will guide to generate key, oauth key, tokens
	}

}