package org.shlimtech.TypeOneBackend.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.shlimtech.TypeOneBackend.dto.ArticleDTO;
import org.shlimtech.TypeOneBackend.mapper.ArticleMapper;
import org.shlimtech.TypeOneBackend.model.Article;
import org.shlimtech.TypeOneBackend.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ArticlesService {

    private ArticlesRepository articlesRepository;
    private ArticleMapper articleMapper;

    @Transactional
    public List<ArticleDTO> findAll() {
        return articlesRepository.findAll().stream().map(articleMapper::articleToArticleDTO).toList();
    }

    @Transactional
    public void remove(int id) {
        articlesRepository.deleteById(id);
    }

    @Transactional
    public void add(ArticleDTO article) {
        articlesRepository.save(articleMapper.articleDTOToArticle(article));
    }

    @Transactional
    public void like(int id) {
        Article article = articlesRepository.getReferenceById(id);
        article.setCurrentLikes(article.getCurrentLikes() + 1);
    }

    @Transactional
    public void unlike(int id) {
        Article article = articlesRepository.getReferenceById(id);
        article.setCurrentLikes(article.getCurrentLikes() - 1);
    }

}
