package api.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.user;
import io.restassured.response.Response;

public class UserTestcases {

	Faker faker;
	user payload;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		payload = new user();

		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		payload.setUserStatus(faker.number().numberBetween(0, 2));

	}

	@Test(priority = 1)
	public void testPostUser() {
		Response resp = UserEndpoints.createUser(payload);
		Assert.assertEquals(resp.statusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByname() {
		Response resp = UserEndpoints.readUser(this.payload.getUsername());
		Assert.assertEquals(resp.statusCode(), 200);
	}

	@Test(priority = 3)
	public void testupdateUser() {
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());

		Response resp = UserEndpoints.updateUser(this.payload.getUsername(), payload);
		Assert.assertEquals(resp.statusCode(), 200);

		Response respafterupdate = UserEndpoints.readUser(this.payload.getUsername());
		Assert.assertEquals(respafterupdate.statusCode(), 200);

	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response resp = UserEndpoints.deleteUser(this.payload.getUsername());
		Assert.assertEquals(resp.statusCode(), 200);
	}

}
