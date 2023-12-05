package org.shlimtech.TypeOneBackend.mapper;

import org.shlimtech.TypeOneBackend.dto.UserDTO;
import org.shlimtech.TypeOneBackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

}
