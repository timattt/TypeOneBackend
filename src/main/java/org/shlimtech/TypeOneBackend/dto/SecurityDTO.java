package org.shlimtech.TypeOneBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecurityDTO {
    private String name;
    private String password;
}