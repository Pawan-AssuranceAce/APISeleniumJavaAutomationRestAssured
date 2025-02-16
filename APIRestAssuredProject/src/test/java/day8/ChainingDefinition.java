/// https://www.youtube.com/watch?v=3jO-Pkyrdo8


/// Chaining : Chaining when one API response is used to as request for another API.
/// For eg: One api is called which have the "Id" data in response...
///         Now that "ID" from response is used to as a request for second API.
////        ID response can be used for different request as userdetails, userupdate, userdelete.
/////       APIOneResponse---Act as Request for---->Second API-------->SecondAPI response
//// This is known as Chaining


///// Chaining can be done in 2 ways.
///// Method1 : Direct methond it means all API test as in same class present..
///             for eg: firstapi response is used as for other API's request and all those other API are presesnt in same class.
/////           So its easy to maintain the sequence of chaining by using the TestPriority.
/////  Method2: Where all API's are in different class ...
////            for eg: Create User in one class, Update user in second class , Delete user in Third class so on.
///             To maintain chaing we will use the TESTNG.XML file

////// WE are going to implement createuser----->GetUser---->Updateuser----->DeleteUser ...all will be in different 
package day8;

public class ChainingDefinition {

}
