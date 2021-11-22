package com.ta.steps;

import com.ta.pojos.AbstractPojo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestParameters {
    public TestSteps testSteps = new TestSteps();

    public void assertAllArrayEquals(Object[][] expected, List<? extends AbstractPojo> actual){
        for (int i = 0; expected.length > i; i++ ) {
            assertArrayEquals(expected[i], actual.get(i).toList());
        }
    }
}
