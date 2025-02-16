//// https://www.youtube.com/watch?v=CyPsHcvl0vE&t=1730s
package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.Pojo_PostRequest;


public class SerializationandDeSerialization {
	
	//// Body(json)---------->Request------------>Response(json)
	///// Body is sent as Json------>Request-------->Give response in Json
	//// Json is light wait
	/// 4 ways to create the Body
	/// 1. POJO This is Famous for eg: Pojo---->Json ....Pojo converted into Json to send as Request
	//// 2. HashMap
	//// 3. Json This Famous too
	///// 4.gson
	
	
	/////**** Serialization is Pojo-----> json .... coverting Pojo to json
	///// **** De-serialization is Json----->Pojo ....coverting json to pojo
	/////// Pojo -----Serailization----->Json Object-----Deserialization-------->Pojo
	//// Serialization for eg : .contentType("application/json") Content is means sending request file in json format
	/////          .body(data) /// Here data is object we cannot send the Object to  request because its not safe...so data as request will be send in form of content-type which json(application/json)
	/////// De-serialization for eg: .post("https://reqres.in/api/users") Response we get into form of Json
	/////////						 .jsonPath().getInt("id"); ///This json reponse covereted into object for validation here we are using jsonPath
	////// Library used for this binding serailization and de serialization is Jackson..with new old rest assured it comes along ..otherwise with old rest assured we need to add in pom.xml
	
	@Test
	//// Pojo ==========> Json (Serilization)
	void convertPojo2Json() throws JsonProcessingException
	{
		///Create Pojo class(encapsulations) under same package use here.. now create object to access the Pojo class
		StudentPojo stupojo = new StudentPojo(); ///pojo class object ////Rightnow data in Java Object
		stupojo.setName("Scott");
		stupojo.setLocation("france");
		stupojo.setPhone("1245689");
		String courseArr[] = {"C","C++"};
		stupojo.setCourses(courseArr);
		
		
		///Convert Java Object ---Serialization---> Json Object
		ObjectMapper objMapper= new ObjectMapper();
		
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		
		System.out.println(jsondata);
	}
	
	@Test
	//// Json ==========> Pojo  (de-Serilization)
	void convertJson2Pojo() throws JsonProcessingException
	{
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"france\",\r\n"
				+ "  \"phone\" : \"1245689\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		/// Converting json data ----->Pojo object
		ObjectMapper objMapper= new ObjectMapper();
		StudentPojo stupojo=objMapper.readValue(jsondata, StudentPojo.class); //Convert Json to Pojo
		
		System.out.println("Name:"+stupojo.getName());
		System.out.println("Location:"+stupojo.getLocation());
		System.out.println("Course1:"+stupojo.getCourses()[0]);
		System.out.println("Course1:"+stupojo.getCourses()[1]);
		System.out.println("Phone:"+stupojo.getPhone());
		
		///// In rest assured is already taken care no need to manually
	}
	
	

}
