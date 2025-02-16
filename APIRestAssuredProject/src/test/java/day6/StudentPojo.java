//// https://www.youtube.com/watch?v=CyPsHcvl0vE&t=1730s
package day6;

public class StudentPojo {
	/*How to create automatic setter and getter for all variable 1st write the variable like this 
	 * and then select all these given below variable goto->Source->generate getters and setters
	String name;
	String location;
	String phone;
	String courses[];
	*/
	
	String name;
	String location;
	String phone;
	String courses[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	
}
