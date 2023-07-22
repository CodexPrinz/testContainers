package com.projects.testContainers.service;

import com.projects.testContainers.exception.UserNotFoundException;
import com.projects.testContainers.model.UserDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
   @Autowired
    private UserService userService;

    @Container
    public static MySQLContainer container = new MySQLContainer("mysql:latest")
            .withDatabaseName("example_db")
            .withUsername("Test")
            .withPassword("Test");

    @BeforeAll
    public static void setUp(){
        container.withReuse(true);
        container.withInitScript("src/main/resources/db.sql");
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.driver-class-name", container::getDriverClassName);
    }
    @Test
    void testCreateUser() {
        UserDto request = new UserDto();
        request.setEmail("Sameer@abc.com");
        request.setName("Sameer");
        request.setCreated_at(new Timestamp(System.currentTimeMillis()));

        UserDto response = userService.createUser(request);
        assertNotNull(response);

    }

    @Test
    void testFindUserByEmailNotFound() {
        Exception exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.findUserByEmail("Sameer1@abc.com")
        );

        assertEquals("User Not Found", exception.getMessage());
    }

    @AfterAll
    public static void tearDown(){
        container.stop();
    }
}