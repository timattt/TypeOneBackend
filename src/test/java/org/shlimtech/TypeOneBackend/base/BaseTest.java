package org.shlimtech.TypeOneBackend.base;

import lombok.RequiredArgsConstructor;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource(properties = {
        "BACKEND_PORT = 8080",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa"
})
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class BaseTest {

    @Autowired
    protected TestAuthHelper testAuthHelper;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected UsersService usersService;
    @Autowired
    protected TestHelpers testHelpers;

}
