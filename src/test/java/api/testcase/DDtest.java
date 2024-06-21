package api.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtest {

	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		user userPayload=new user();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
			Response response=UserEndpoints.deleteUser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
	
	
	
}


