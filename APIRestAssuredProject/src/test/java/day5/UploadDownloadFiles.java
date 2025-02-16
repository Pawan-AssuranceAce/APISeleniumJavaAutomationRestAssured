/// https://www.youtube.com/watch?v=IB3G7IbdD1k 
/// Video start from 50 min

/// Pre-requites we need to run java -jar file-upload-RestAPI.jar in cmd where the jar file is kept
//// Launch the http://localhost:8080/ in any browser
package day5;

import org.testng.annotations.Test;
//Every test file we need to add manually there given below 3 static packages which
//identify the given when then methods
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.io.File;
public class UploadDownloadFiles {
	
	@Test(priority=1)
	void singleFileUpload()
	{
		/// we are going to upload the file and those files will get uploaded in you c drive in upload folder because we run the file-upload-RestAPI.jar it created one folder in c drive
		File myfile=new File("C:\\SampleFiles\\Text1.txt");
		given()
			//now add the form data similarly we did in postman ///this is also takes two parameter key and value
			.multiPart("file",myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile") ///Request URL to upload file
		.then()
		///we can verify the response
			.statusCode(200)
			.body("fileName",equalTo("Text1.txt"))
			.log().all();
				
	}
	
	@Test(priority=2)
	void multipleFileUpload()
	{
		/// we are going to upload the two files and those files will get uploaded in you c drive upload folder because we run the file-upload-RestAPI.jar it created one folder in c drive
		File myfile1=new File("C:\\SampleFiles\\Text2.txt");
		File myfile2=new File("C:\\SampleFiles\\Text3.txt");
		given()
			//now add the form data similarly we did in postman ///this is also takes two parameter key and value
		///Note here it will be files not file
			.multiPart("files",myfile1)
			.multiPart("files",myfile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") ///Request URL to upload file
		.then()
		///we can verify the response
			.statusCode(200)
			.body("[0].fileName",equalTo("Text2.txt"))
			.body("[1].fileName",equalTo("Text3.txt"))///starting with zero becoz no name is there before that
			.log().all();
				
	}
	
	///@Test(priority=3) /// in some api this  deos not works all time suppose we have the 100 files then we can pass as arr[]
	void multipleFileUpload100files()
	{
		/// we are going to upload the two files and those files will get uploaded in you c drive upload folder because we run the file-upload-RestAPI.jar it created one folder in c drive
		File myfile1=new File("C:\\SampleFiles\\Text2.txt");
		File myfile2=new File("C:\\SampleFiles\\Text3.txt");
		File filearr[] = {myfile1,myfile2};
		given()
			//now add the form data similarly we did in postman ///this is also takes two parameter key and value
		///Note here it will be files not file
			.multiPart("files",filearr)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") ///Request URL to upload file
		.then()
		///we can verify the response
			.statusCode(200)
			.body("[0].fileName",equalTo("Text2.txt"))
			.body("[1].fileName",equalTo("Text3.txt"))///starting with zero becoz no name is there before that
			.log().all();
				
	}
	
	@Test(priority=4)
	void fileDownload()
	{
		given()
		.when()
			.get("http://localhost:8080/downloadFile/Text3.txt")
		.then()
			.statusCode(200)
			.log().body();
	}

}
