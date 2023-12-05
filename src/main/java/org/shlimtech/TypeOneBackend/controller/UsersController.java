package org.shlimtech.TypeOneBackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.TypeOneBackend.dto.SecurityDTO;
import org.shlimtech.TypeOneBackend.dto.UserDTO;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@Log
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody SecurityDTO dto) {
        UserDTO userDTO = usersService.register(dto);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SecurityDTO dto) {
        String token = usersService.login(dto);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
