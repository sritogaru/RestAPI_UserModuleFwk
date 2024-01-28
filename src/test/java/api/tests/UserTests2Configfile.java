package api.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsConfigFile;
import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserTests2Configfile {
	
	Faker fake;
	UserPOJO userpayload;
 Logger logger;
	
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
		
		//logs
				logger= LogManager.getLogger(this.getClass());
				
				logger.debug("debugging.....");
				
		
		
	}
	
	@Test(priority=1)
	public void testcreateUser(ITestContext context) {
		
		logger.info("********** Creating user  ***************");
		
		Response res=UserEndPointsConfigFile.createuser(userpayload);
		res.then().log().all();
		
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		logger.info("********** User created ***************");
		
	}
	@Test(priority=2)
	public void testgetUser() {
		
		logger.info("********** get user info ***************");
		
		Response res=UserEndPointsConfigFile.getuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		logger.info("********** user info is displayed ***************");
		
	}
	@Test(priority=3)
	public void testupdateUser() {
		
		logger.info("********** update user  ***************");
		
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setPhone(fake.phoneNumber().cellPhone());
		
		Response res=UserEndPointsConfigFile.updateuser(this.userpayload.getUsername(), userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		logger.info("********** updating user is completed  ***************");
		
		//checking updated data
		
		Response resafterupdate=UserEndPointsConfigFile.getuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(resafterupdate.getStatusCode(), 200);
		Assert.assertEquals(resafterupdate.getContentType(), "application/json");
		
		logger.info("********** Checking update data of user  ***************");
		
		
	}
	@Test(priority=4)
	public void testdeleteUser() {
		
		logger.info("********** Delete user  ***************");
		
		Response res=UserEndPointsConfigFile.deleteuser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		logger.info("********** User is deleted ***************");
		
	}

}
