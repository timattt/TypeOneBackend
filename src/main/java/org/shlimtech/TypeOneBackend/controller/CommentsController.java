package org.shlimtech.TypeOneBackend.controller;

import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> index() {
        return Map.of("ggg", "hhhh");
    }

}
