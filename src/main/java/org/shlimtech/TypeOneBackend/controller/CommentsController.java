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
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO comment) {
        CommentDTO res = commentsService.add(comment);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        commentsService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/like")
    public ResponseEntity<CommentDTO> like(@RequestParam int id) {
        CommentDTO res = commentsService.like(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/unlike")
    public ResponseEntity<CommentDTO> unlike(@RequestParam int id) {
        CommentDTO res = commentsService.unlike(id);
        return ResponseEntity.ok(res);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
