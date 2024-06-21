package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints_log {

	public static Response createUser(user Payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(Payload).when()
				.post(Routes.post_url);
		return response;
	}

	public static Response readUser(String userName) {
		Response resp = given().pathParam("username", userName).when().get(Routes.get_url);

		return resp;
	}

	public static Response updateUser(String userName, user payload) {

		Response resp = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName)
				.body(payload).when().put(Routes.update_url);

		return resp;
	}

	public static Response deleteUser(String userName) {
		Response resp = given().pathParam("username", userName).when().delete(Routes.delete_url);

		return resp;
	}

}
