package com.akash.spring_junit_test_app.user;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExampleTests {

    @BeforeEach
    public void beforeEachTest(){
        System.out.println("Next test is about to start");
    }

    @AfterEach
    public void afterEachTest(){
        System.out.println("Current test is completed");
    }

    @BeforeAll
    public static void beforeAllTests(){
        System.out.println("All tests about to start");
    }

    @AfterAll
    public static void afterAllTests(){
        System.out.println("All tests completed");
    }

    @Test
    @Disabled("comments on disabled state")
    public void basicTest(){
        assertEquals(1,1);
        System.out.println("tested for disabled");
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void testForWindowsOnly(){
        assertEquals(1,1);
        System.out.println("tested for Windows");
    }

    @Test
    @EnabledOnOs(OS.MAC)
    public void testForMacOnly(){
        assertEquals(1,1);
        System.out.println("tested for Mac");
    }

    @Test
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    public void testForWindowsAndMac(){
        assertEquals(1,1);
        System.out.println("tested for Windows and Mac");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    public void testForOnlyJava17(){
        assertEquals(1,1);
        System.out.println("tested for Java 17");
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_8, max=JRE.JAVA_17)
    public void testOnlyForJavaRange(){
        assertEquals(1,1);
        System.out.println("tested for Java 8 to Java 17");
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11)
    public void testOnlyForMinJav(){
        assertEquals(1,1);
        System.out.println("tested for min Java 8");
    }
}
