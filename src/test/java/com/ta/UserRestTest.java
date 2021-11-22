package com.ta;

import com.ta.pojos.*;
import java.util.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserRestTest extends TestParameters {

    @Test
    @Tag("positive")
    public void getUserParametersTest() {
        UserPojo userParameters = testSteps.getUser();
        Object [] expectedParameters = new Object[] {2, "janet.weaver@reqres.in", "Janet", "Weaver"};

        assertArrayEquals(expectedParameters, userParameters.toList());
    }
    @Test
    @Tag("positive")
    public void getListUsersTest() {
        List<UserPojo> listUserParameters = testSteps.getListUsers();
        Object [] [] expectedParameters = new Object[][] {
                {1, "george.bluth@reqres.in", "George", "Bluth"},
                {2, "janet.weaver@reqres.in", "Janet", "Weaver"},
                {3, "emma.wong@reqres.in", "Emma", "Wong"},
                {4, "eve.holt@reqres.in", "Eve", "Holt"},
                {5, "charles.morris@reqres.in", "Charles", "Morris"},
                {6, "tracey.ramos@reqres.in", "Tracey", "Ramos"}
        };

        assertAllArrayEquals(expectedParameters, listUserParameters);
    }

    @Test
    @Tag("negative")
    public void notFoundUserTest(){
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/api/users/23")
                .when().get()
                .then().statusCode(404);
    }

    @Test
    @Tag("positive")
    public void createUserTest(){
        RequestUserPojo newUserParameters = testSteps.generateUser("Grigory", "driver");
        ResponsePostUserPojo respondedUser = testSteps.createUser(newUserParameters);

        assertArrayEquals(newUserParameters.toList(), respondedUser.toList());
        assertTrue(newUserParameters.isSameDate(respondedUser.getCreatedAt()));
    }

    @Test
    @Tag("positive")
    public void putUpdateUserTest(){
        RequestUserPojo newUserParameters = testSteps.generateUser("morpheus", "leader");
        UpdateUserPojo respondedUser = testSteps.putUpdateUser(newUserParameters);

        assertArrayEquals(newUserParameters.toList(), respondedUser.toList());
        assertTrue(newUserParameters.isSameDate(respondedUser.getUpdatedAt()));
    }

    @Test
    @Tag("positive")
    public void patchUpdateUserTest(){
        RequestUserPojo newUserParameters = testSteps.generateUser("morpheus", "leader");
        UpdateUserPojo respondedUser = testSteps.patchUpdateUser(newUserParameters);

        assertArrayEquals(newUserParameters.toList(), respondedUser.toList());
        assertTrue(newUserParameters.isSameDate(respondedUser.getUpdatedAt()));
    }

    @Test
    @Tag("positive")
    public void deleteUserTest(){
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/users/2")
                .when().delete()
                .then().statusCode(204);
    }
}
