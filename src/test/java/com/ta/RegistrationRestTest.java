package com.ta;

import com.ta.pojos.RequestLoginPojo;
import com.ta.pojos.ResponseRegistrationPojo;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationRestTest extends TestParameters {
    @Test
    @Tag("positive")
    public void successRegistrationTest(){
        RequestLoginPojo createNewUserParameters = testSteps.generateLogin("eve.holt@reqres.in", "pistol");
        ResponseRegistrationPojo respondedParameters = testSteps.registrationRequest(createNewUserParameters);

        assertTrue(respondedParameters.getId() > 0);
        assertNotNull(respondedParameters.getToken());
    }
    @Test
    @Tag("negative")
    public void unSuccessRegistrationTest(){
        RequestLoginPojo createNewUserParameters = testSteps.generateLogin("eve.holt@reqres.in", "");

        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/register")
                .body(createNewUserParameters)
                .post()
                .then().statusCode(400);
    }
}
