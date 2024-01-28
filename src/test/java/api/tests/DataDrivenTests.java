package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testpostuser(String uid,String UserName,String fname, String lname, String mail, String pwd, String phn) {
		
		UserPOJO userpayload = new UserPOJO();
		
		userpayload.setId(Integer.parseInt(uid));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(mail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phn);
	

		Response res=UserEndPoints.createuser(userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
	}
	@Test(priority=3,dataProvider="UserNames",dataProviderClass = DataProviders.class)
	public void testgetuserbyUserName(String UserName) {
		
		Response res=UserEndPoints.getuser(UserName);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		
		
	}

	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass = DataProviders.class)
	public void testdeleteuserbyUserName(String UserName) {
		
		Response res=UserEndPoints.deleteuser(UserName);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getContentType(), "application/json");
		
		
		
	}
	
	
}
