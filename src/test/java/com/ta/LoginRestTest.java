package com.ta;

import com.ta.pojos.RequestLoginPojo;
import com.ta.pojos.ResponseAuthorizationPojo;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginRestTest extends TestParameters {
    @Test
    public void successLoginTest(){
        RequestLoginPojo requestLogin = testSteps.generateLogin("eve.holt@reqres.in", "cityslicka");
        ResponseAuthorizationPojo respondedParameters = testSteps.authorizationRequest(requestLogin);

        assertNotNull(respondedParameters.getToken());
    }
    @Test
    public void unSuccessLoginTest(){
        RequestLoginPojo requestLogin = testSteps.generateLogin("eve.holt@reqres.in", "");
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/login")
                .body(requestLogin)
                .post()
                .then().statusCode(400);
    }
}
