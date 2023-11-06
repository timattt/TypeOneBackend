package org.shlimtech.TypeOneBackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.TypeOneBackend.dto.ArticleDTO;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.shlimtech.TypeOneBackend.service.ArticlesService;
import org.shlimtech.TypeOneBackend.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentsController {

    private CommentsService commentsService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> index() {
        return ResponseEntity.ok(commentsService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO comment) {
        commentsService.add(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        commentsService.remove(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.ok().build();
    }

}
