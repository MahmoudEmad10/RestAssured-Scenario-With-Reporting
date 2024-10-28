import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import Base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import pojos.createNewUser;
import pojos.registerAndLoginUsers;
import Listeners.TestNGListeners;

@Listeners(TestNGListeners.class)
public class ExampleTest extends BaseTest {

    //Post Methods
    @Test
    public void RegisterUser() {
        registerAndLoginUsers registerUsers = new registerAndLoginUsers(config.getemail(), config.getPassword());
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(registerUsers)
                .post(config.getBaseUrl() + "/api/register")
                .then()
                .statusCode(200)
                .extract().response();
        assertEquals(response.getStatusCode(), 200, "Status code mismatch!");


        int id = response.jsonPath().getInt("id");
        String token = response.jsonPath().getString("token");

        System.out.println(id);
        System.out.println(token);


    }

    @Test
    public void RegisterUserUnsuccessfully() {

        registerAndLoginUsers registerUsers = new registerAndLoginUsers(config.getemail(), "");
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(registerUsers)
                .post(config.getBaseUrl() + "/api/register")
                .then()
                .statusCode(400)
                .extract().response();
        String error = response.jsonPath().getString("error");


        System.out.println(error);

    }

    @Test
    public void CreateUser() {
        createNewUser createNewUser = new createNewUser(config.getname(), config.getJob());
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(createNewUser)
                .post(config.getBaseUrl() + "/api/users")
                .then()
                .statusCode(201)
                .extract().response();


        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String id = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("createdAt");

        assertEquals(name, config.getname());
        assertEquals(job, config.getJob());
        Assert.assertNotNull(id);
        Assert.assertNotNull(createdAt);


        System.out.println(name);
        System.out.println(job);

    }

    @Test
    public void userLogin() {
        registerAndLoginUsers loginUsers = new registerAndLoginUsers(config.getemail(), config.getPassword());
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(loginUsers)
                .post(config.getBaseUrl() + "/api/login")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");
        String token = response.jsonPath().getString("token");

        System.out.println(token);
    }

    @Test
    public void userLoginUnsuccessfully() {
        registerAndLoginUsers loginUsers = new registerAndLoginUsers(config.getemail(), "");
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(loginUsers)
                .post(config.getBaseUrl() + "/api/login")
                .then()
                .statusCode(400)
                .extract().response();

        String error = response.jsonPath().getString("error");

        System.out.println(error);
    }

    //Get Methods
    @Test
    public void getListOfUsersForSpecificPage() {
        Response response = given().header("Content-Type", "application/json")
                .queryParam("page", config.getSpecificPage())
                .when()
                .get(config.getBaseUrl() + "/api/users")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void getSingleUser() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .get(config.getBaseUrl() + "/api/users/" + config.getspecificUserID())
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void getSingleUserNotFound() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .get(config.getBaseUrl() + "/api/users/" + config.getSingleUserNotFound())
                .then()
                .statusCode(404)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void getListResources() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .get(config.getBaseUrl() + "/api/unknown")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void getSingleResources() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .get(config.getBaseUrl() + "/api/unknown/" + config.getspecificUserID())
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void getSingleResourceNotFound() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .get(config.getBaseUrl() + "/api/unknown/" + config.getSingleUserNotFound())
                .then()
                .statusCode(404)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());

    }

    @Test
    public void delayedResponse() {
        Response response = given().header("Content-Type", "application/json")
                .queryParam("delay", config.getDelay())
                .when()
                .get(config.getBaseUrl() + "/api/users")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettify());
    }

    @Test
    //Update Methods
    public void updateUserUsingPut() {
        createNewUser updateUser = new createNewUser(config.getname(), config.getJob());
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(updateUser)
                .put(config.getBaseUrl() + "/api/users/" + config.getspecificUserID())
                .then()
                .statusCode(200)
                .extract().response();

        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String updatedAt = response.jsonPath().getString("updatedAt");

        Assert.assertNotNull(updatedAt);


        System.out.println(name);
        System.out.println(job);
        System.out.println(updatedAt);
    }

    @Test
    public void updateUserUsingPatch() {
        createNewUser updateUser = new createNewUser(config.getname(), config.getJob());
        Response response = given().header("Content-Type", "application/json")
                .when()
                .body(updateUser)
                .patch(config.getBaseUrl() + "/api/users/" + config.getspecificUserID())
                .then()
                .statusCode(200)
                .extract().response();

        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");
        String updatedAt = response.jsonPath().getString("updatedAt");

        Assert.assertNotNull(updatedAt);


        System.out.println(name);
        System.out.println(job);
        System.out.println(updatedAt);
    }

    // Delete Methods
    @Test
    public void deleteUser() {
        Response response = given().header("Content-Type", "application/json")
                .when()
                .delete(config.getBaseUrl() + "/api/users/" + config.getspecificUserID())
                .then()
                .statusCode(204)
                .extract().response();
    }

}
