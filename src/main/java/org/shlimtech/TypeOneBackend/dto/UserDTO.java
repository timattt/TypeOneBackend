package org.shlimtech.TypeOneBackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private int id;
    private String name;
}
