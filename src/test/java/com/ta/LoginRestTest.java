package com.ta;

import com.ta.pojos.RequestLoginPojo;
import com.ta.pojos.ResponseAuthorizationPojo;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginRestTest extends TestParameters {
    @Test
    @Tag("positive")
    public void successLoginTest(){
        RequestLoginPojo createNewUserParameters = testSteps.generateLogin("eve.holt@reqres.in", "cityslicka");
        ResponseAuthorizationPojo respondedParameters = testSteps.authorizationRequest(createNewUserParameters);

        assertNotNull(respondedParameters.getToken());
    }
    @Test
    @Tag("negative")
    public void unSuccessLoginTest(){
        RequestLoginPojo createNewUserParameters = testSteps.generateLogin("eve.holt@reqres.in", "");
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/login")
                .body(createNewUserParameters)
                .post()
                .then().statusCode(400);
    }
}
