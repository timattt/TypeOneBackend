package org.shlimtech.TypeOneBackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.shlimtech.TypeOneBackend.service.CommentsService;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentsController {

    private CommentsService commentsService;
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> index() {
        return ResponseEntity.ok(commentsService.findAll());
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO comment) {
        CommentDTO res = commentsService.add(usersService.getCurrentUser(), comment);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        commentsService.checkUserRightsForComments(usersService.getCurrentUser(), id);
        commentsService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<CommentDTO> editContent(@RequestBody CommentDTO commentDTO) {
        commentsService.checkUserRightsForComments(usersService.getCurrentUser(), commentDTO.getCommentId());
        CommentDTO res = commentsService.editContent(commentDTO);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<CommentDTO> like(@PathVariable int id) {
        commentsService.checkUserRightsForComments(usersService.getCurrentUser(), id);
        CommentDTO res = commentsService.like(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}/unlike")
    public ResponseEntity<CommentDTO> unlike(@PathVariable int id) {
        commentsService.checkUserRightsForComments(usersService.getCurrentUser(), id);
        CommentDTO res = commentsService.unlike(id);
        return ResponseEntity.ok(res);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
