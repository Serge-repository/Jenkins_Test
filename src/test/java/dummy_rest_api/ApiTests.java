package dummy_rest_api;

import api_models.Data;
import api_models.post.PostNewEmployeeRequestModel;
import api_models.post.PostNewEmployeeResponseModel;
import api_models.put.PutEmployeeRequestModel;
import api_models.put.PutEmployeeResponseModel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.LogListener;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;

@Listeners({LogListener.class})
public class ApiTests {
    @Parameters({"env"})
    @BeforeMethod
    public void setup(final String env) {
        RestAssured.baseURI = env;
    }

    @Test
    public void getAllEmployeeDataPositive() {
        get("/api/v1/employees")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .time(lessThan(2500L))
                .and()
                .contentType("application/json")
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id[4]", Matchers.is(5), "data.employee_name[4]", Matchers.equalTo("Airi Satou"));
    }

    @Test
    public void getAllEmployeeDataNegative() {
        get("/api/v1/employees")
                .then()
                .log().all()
                .and()
                .time(lessThan(1000L))
                .contentType("application/json")
                .assertThat()
                .body("data.id[4]", Matchers.is(5), "data.employee_name[4]", Matchers.equalTo("Airi Satou"))
                .body("data.employee_age[9]", Matchers.blankString(), "data.employee_salary[9]",
                        Matchers.anyOf(Matchers.lessThan("400"), Matchers.greaterThan("500")));
    }

    @Test
    public void getFirstEmployeePositive() {
        get("/api/v1/employee/1")
                .then()
                .log().all()
                .contentType("application/json")
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully! Record has been fetched."))
                .body("data.id", Matchers.is(1),
                        "data.employee_name", Matchers.equalTo("Tiger Nixon"),
                        "data.employee_salary", Matchers.equalTo(320800),
                        "data.employee_age", Matchers.equalTo(61));

    }

    @Test
    public void getFirstEmployeeNegative() {
        get("/api/v1/employee/1")
                .then()
                .log().all()
                .contentType("application/json")
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("Test to be failed"))
                .body("data.id", Matchers.is(1),
                        "data.employee_name", Matchers.equalTo("Tiger Woods"),
                        "data.employee_salary", Matchers.equalTo(320800),
                        "data.employee_age", Matchers.equalTo(21));

    }

    @Test
    public void postNewEmployeePositive() {
        PostNewEmployeeRequestModel postRequestNewEmployeeObject = new PostNewEmployeeRequestModel("test", "123", "23");
        PostNewEmployeeResponseModel expectedResponseModelObject = new PostNewEmployeeResponseModel("success",
                new Data("test", "123", "23"), "Successfully! Record has been added.");

        PostNewEmployeeResponseModel responseNewEmployeeObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestNewEmployeeObject)
                .log().all()
                .when()
                .request("POST", "/api/v1/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(PostNewEmployeeResponseModel.class);
        assertEquals(responseNewEmployeeObject, expectedResponseModelObject);
    }

    @Test
    public void postNewEmployeeNegative() {
        PostNewEmployeeRequestModel postRequestNewEmployeeObject = new PostNewEmployeeRequestModel("guest", "999", "156");
        PostNewEmployeeResponseModel expectedResponseModelObject = new PostNewEmployeeResponseModel("success",
                new Data("test", "123", "23"), "Successfully! Record has been added.");

        PostNewEmployeeResponseModel responseNewEmployeeObject = given()
                .with()
                .contentType("application/json")
                .body(postRequestNewEmployeeObject)
                .log().body()
                .when()
                .request("POST", "/api/v1/create")
                .then()
                .log().body()
                .extract()
                .as(PostNewEmployeeResponseModel.class);
        assertEquals(responseNewEmployeeObject, expectedResponseModelObject);
    }

    @Test//(dependsOnMethods = {"postNewEmployeePositive"})
    public void putEmployeePositive() {
        PutEmployeeRequestModel putRequestEmployeeObject = new PutEmployeeRequestModel("Matt", "3000", "28");
        PutEmployeeResponseModel putResponseModelObject = new PutEmployeeResponseModel("success",
                new Data("Matt", "3000", "28"), "Successfully! Record has been updated.");

        PutEmployeeResponseModel responseNewEmployeeObject = given()
                .with()
                .contentType("application/json")
                .body(putRequestEmployeeObject)
                .log().all()
                .when()
                .request("PUT", "/api/v1/update/21")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .as(PutEmployeeResponseModel.class);
        assertEquals(responseNewEmployeeObject, putResponseModelObject);
    }

    @Test//(dependsOnMethods = {"postNewEmployeePositive"})
    public void putEmployeeNegative() {
        PutEmployeeRequestModel putRequestEmployeeObject = new PutEmployeeRequestModel("Matt", "3000", "28");
        given()
                .with()
                .contentType("application/json")
                .body(putRequestEmployeeObject)
                .log().all()
                .when()
                .request("PUT", "/api/v1/update/21")
                .then()
                .log().body()

                .assertThat()
                .body("status", equalTo("failure"))

                .extract()
                .as(PutEmployeeResponseModel.class);
        assertThat("message", Matchers.equalTo("Successfully! Record has been added."));
    }

    @Test
    public void deleteEmployeePositive() {
        when()
                .request("DELETE", "/api/v1/delete/2")
                .then()
                .log().everything()
                .header("connection", "keep-alive")
                .and()
                .statusCode(200)
                .and()
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully! Record has been deleted"))
                .body("data", equalTo("2"));
    }

    @Test
    public void deleteEmployeeNegative() {
        when()
                .request("DELETE", "/api/v1/delete/2")
                .then()
                .log().everything()
                .header("connection", "keep-alive")
                .and()
                .assertThat()
                .body("data", equalTo("333"));
        assertThat("message", Matchers.equalTo("No record found"));
    }
}
