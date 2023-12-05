package org.shlimtech.TypeOneBackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.TypeOneBackend.dto.ArticleDTO;
import org.shlimtech.TypeOneBackend.service.ArticlesService;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
@Log
public class ArticlesController {

    private ArticlesService articlesService;
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> index() {
        return ResponseEntity.ok(articlesService.findAll());
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> addArticle(@RequestBody ArticleDTO article) {
        ArticleDTO res = articlesService.add(usersService.getCurrentUser(), article);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable int id) {
        articlesService.checkUserRightsForArticle(usersService.getCurrentUser(), id);
        articlesService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<ArticleDTO> editContent(@RequestBody ArticleDTO article) {
        articlesService.checkUserRightsForArticle(usersService.getCurrentUser(), article.getArticleId());
        ArticleDTO res = articlesService.editContent(article);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<ArticleDTO> like(@PathVariable int id) {
        articlesService.checkUserRightsForArticle(usersService.getCurrentUser(), id);
        ArticleDTO res = articlesService.like(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}/unlike")
    public ResponseEntity<ArticleDTO> unlike(@PathVariable int id) {
        articlesService.checkUserRightsForArticle(usersService.getCurrentUser(), id);
        ArticleDTO res = articlesService.unlike(id);
        return ResponseEntity.ok(res);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
