package org.shlimtech.TypeOneBackend.base;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.shlimtech.TypeOneBackend.dto.SecurityDTO;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@Component
public class TestAuthHelper {

    private final MockMvc mockMvc;
    private final UsersService usersService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void registerUser(String name, String pass) {
        SecurityDTO dto = new SecurityDTO(name, pass);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk());
        Assert.assertTrue(usersService.loadUserByUsername(name).getPassword().equals(pass));
    }

    @SneakyThrows
    public String loginUser(String name, String pass) {
        SecurityDTO dto = new SecurityDTO(name, pass);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Map<String, String> resp = objectMapper.readValue(mvcResult, Map.class);
        return resp.get("token");
    }

}
