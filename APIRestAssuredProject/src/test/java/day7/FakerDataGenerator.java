/// https://www.youtube.com/watch?v=SBp5rStA3vQ

package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

////we have "Faker lib" which we added into POM.XML... This Faker lib can generate the fake data like student,name,internet data, book...so on
///which we can use for generating data in Post()..and aviod to hard code the data... and we use faker data lib for testing test will multiple data


public class FakerDataGenerator {
	
	
	@Test 
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
		String fullname=faker.name().fullName();
		String firstname=faker.name().firstName();
		String lastname=faker.name().lastName();
		String username=faker.name().username();
		String password=faker.internet().password();
		String phoneno=faker.phoneNumber().cellPhone();
		String email=faker.internet().safeEmailAddress();
		
		System.out.println("fullname:"+fullname);
		System.out.println("firstname:"+firstname);
		System.out.println("lastname:"+lastname);
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("phoneno:"+phoneno);
		System.out.println("email:"+email);
		
	}

}
