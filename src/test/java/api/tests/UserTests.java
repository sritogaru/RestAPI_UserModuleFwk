package api.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserTests {
	
	Faker fake;
	UserPOJO userpayload;
	
	@BeforeClass
	public void setup() {
		
		fake = new Faker();
		userpayload = new UserPOJO();
		
		//userpayload.setId(fake.idNumber().hashCode());
		userpayload.setUsername(fake.name().username());
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setEmail(fake.internet().emailAddress());
		userpayload.setPassword(fake.internet().password(5, 10));
		userpayload.setPhone(fake.phoneNumber().cellPhone());
		
		
	}
	
	@Test(priority=1)
	public void testcreateUser(ITestContext context) {
		
		Response res=UserEndPoints.createuser(userpayload);
		res.then().log().all();
		
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		
		
	}
	@Test(priority=2)
	public void testgetUser() {
		
		Response res=UserEndPoints.getuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
	}
	@Test(priority=3)
	public void testupdateUser() {
		
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setPhone(fake.phoneNumber().cellPhone());
		
		Response res=UserEndPoints.updateuser(this.userpayload.getUsername(), userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		//checking updated data
		
		Response resafterupdate=UserEndPoints.getuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(resafterupdate.getStatusCode(), 200);
		Assert.assertEquals(resafterupdate.getContentType(), "application/json");
		
		
	}
	@Test(priority=4)
	public void testdeleteUser() {
		
		Response res=UserEndPoints.deleteuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
	}

}
