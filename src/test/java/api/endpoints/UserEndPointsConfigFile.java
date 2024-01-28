package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.UserPOJO;
import io.restassured.response.Response;

public class UserEndPointsConfigFile {
	
	public static ResourceBundle getURL(){ //getting urls from properies file
		
		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
		
	}
	
public static Response createuser(UserPOJO payload) {
	
	    String posturl = getURL().getString("post_url");
		
		Response res=given()
		.accept("application/json")
		.contentType("application/json")
		.body(payload)
		
		.when()
		.post(posturl);
		
		return res;
		
	}
	
public static Response getuser(String UserName) {
	
	String geturl = getURL().getString("get_url");
		
		Response res=given()
		.pathParam("username", UserName)
		
		.when()
		.get(geturl);
		
		return res;
		
	}
public static Response updateuser(String UserName,UserPOJO payload) {
	
	String updateurl = getURL().getString("update_url");
	
	Response res=given()
	.accept("application/json")
	.contentType("application/json")
	.pathParam("username", UserName)
	.body(payload)
	
	
	.when()
	.put(updateurl);
	
	return res;
	
}

public static Response deleteuser(String UserName) {
	
	String deleteurl = getURL().getString("delete_url");
	
	Response res=given()
	.pathParam("username", UserName)
	
	.when()
	.delete(deleteurl);
	
	return res;
	
}


}
