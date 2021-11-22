package com.ta;

import com.ta.pojos.ResourcePojo;
import com.ta.steps.TestParameters;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ResourceRestTest extends TestParameters {
    @Test
    @Tag("positive")
    public void getResourceTest(){
        ResourcePojo resource = testSteps.getResource();
        Object [] expectedParameters = new Object[] {2, "fuchsia rose", 2001, "#C74375", "17-2031"};

        assertArrayEquals(expectedParameters, resource.toList());
    }

    @Test
    @Tag("positive")
    public void getListResourcesTest(){
        List<ResourcePojo> listResources = testSteps.getListResources();
        Object [][] expectedParameters = new Object[][] {
                {1, "cerulean", 2000, "#98B2D1", "15-4020"},
                {2, "fuchsia rose", 2001, "#C74375", "17-2031"},
                {3, "true red", 2002, "#BF1932", "19-1664"},
                {4, "aqua sky", 2003, "#7BC4C4", "14-4811"},
                {5, "tigerlily", 2004, "#E2583E", "17-1456"},
                {6, "blue turquoise", 2005, "#53B0AE", "15-5217"}
        };

        assertAllArrayEquals(expectedParameters, listResources);
    }
    @Test
    @Tag("negative")
    public void notFoundUResourceTest(){
        given()
                .spec(testSteps.getRequestSpecification())
                .basePath("/unknown/23")
                .when().get()
                .then().statusCode(404);
    }
}
