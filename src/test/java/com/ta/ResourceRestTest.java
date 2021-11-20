package com.ta;

import com.ta.pojos.ResourcePojo;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceRestTest extends TestParameters {
    @Test
    public void getResourceTest(){
        ResourcePojo resource = testSteps.getResource();
        Object [] expectedParameters = new Object[] {2, "fuchsia rose", 2001, "#C74375", "17-2031"};

        assertArrayEquals(expectedParameters, resource.toList());
    }

    @Test
    public void getListResourcesTest(){
        List<ResourcePojo> resources = testSteps.getListResources();
        Object [] expectedParameters = new Object[] {2, "fuchsia rose", 2001, "#C74375", "17-2031"};

        assertEquals(6, resources.size());
        assertArrayEquals(expectedParameters, resources.get(1).toList());
    }
    @Test
    public void notFoundUResourceTest(){
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/unknown/23")
                .when().get()
                .then().statusCode(404);
    }
}
