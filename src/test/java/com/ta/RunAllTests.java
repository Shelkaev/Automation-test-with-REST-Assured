package com.ta;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({LoginRestTest.class,
                RegistrationRestTest.class,
                ResourceRestTest.class,
                UserRestTest.class
                })
public class RunAllTests {
}
