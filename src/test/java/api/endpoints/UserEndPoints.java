package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createuser(UserPOJO payload) {
		
		Response res=given()
		.accept("application/json")
		.contentType("application/json")
		.body(payload)
		
		.when()
		.post(Routes.createuser_url);
		
		return res;
		
	}
	
public static Response getuser(String UserName) {
		
		Response res=given()
		.pathParam("username", UserName)
		
		.when()
		.get(Routes.getuser_url);
		
		return res;
		
	}
public static Response updateuser(String UserName,UserPOJO payload) {
	
	Response res=given()
	.accept("application/json")
	.contentType("application/json")
	.pathParam("username", UserName)
	.body(payload)
	
	
	.when()
	.put(Routes.updateuser_url);
	
	return res;
	
}

public static Response deleteuser(String UserName) {
	
	Response res=given()
	.pathParam("username", UserName)
	
	.when()
	.delete(Routes.deleteuser_url);
	
	return res;
	
}

}
