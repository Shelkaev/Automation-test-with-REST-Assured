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
    public void getUserTest() {
        UserPojo user = testSteps.getUser();
        Object [] expectedParameters = new Object[] {2, "janet.weaver@reqres.in", "Janet", "Weaver"};

        assertArrayEquals(expectedParameters, user.toList());
    }
    @Test
    @Tag("positive")
    public void getListUsersTest() {
        List<UserPojo> users = testSteps.getListUsers();
        Object [] expectedParameters = new Object[] {1, "george.bluth@reqres.in", "George", "Bluth"};

        assertEquals(6, users.size());
        assertArrayEquals(expectedParameters, users.get(0).toList());
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
        RequestUserPojo requestedUser = testSteps.generateUser("Grigory", "driver");
        ResponsePostUserPojo respondedUser = testSteps.createUser(requestedUser);

        assertArrayEquals(requestedUser.toList(), respondedUser.toList());
        assertTrue(requestedUser.isSameDate(respondedUser.getCreatedAt()));
    }

    @Test
    @Tag("positive")
    public void putUpdateUserTest(){
        RequestUserPojo requestedUser = testSteps.generateUser("morpheus", "leader");
        UpdateUserPojo respondedUser = testSteps.putUpdateUser(requestedUser);

        assertArrayEquals(requestedUser.toList(), respondedUser.toList());
        assertTrue(requestedUser.isSameDate(respondedUser.getUpdatedAt()));
    }

    @Test
    @Tag("positive")
    public void patchUpdateUserTest(){
        RequestUserPojo requestedUser = testSteps.generateUser("morpheus", "leader");
        UpdateUserPojo respondedUser = testSteps.patchUpdateUser(requestedUser);

        assertArrayEquals(requestedUser.toList(), respondedUser.toList());
        assertTrue(requestedUser.isSameDate(respondedUser.getUpdatedAt()));
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
