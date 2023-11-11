package org.shlimtech.TypeOneBackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.shlimtech.TypeOneBackend.service.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/like")
    public ResponseEntity<?> like(@RequestParam int id) {
        commentsService.like(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/unlike")
    public ResponseEntity<?> unlike(@RequestParam int id) {
        commentsService.unlike(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
