package org.shlimtech.TypeOneBackend.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.shlimtech.TypeOneBackend.dto.ArticleDTO;
import org.shlimtech.TypeOneBackend.model.Article;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleMapper {

    private ModelMapper modelMapper;

    public ArticleDTO articleToArticleDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setArticleId(article.getId());
        dto.setText(article.getText());
        dto.setTitle(article.getTitle());
        dto.setLikesCount(article.getCurrentLikes());
        dto.setCreationTime(article.getCreationTime());
        dto.setUserId(article.getAuthor().getId());
        return dto;
    }

    public Article articleDTOToArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setText(articleDTO.getText());
        article.setTitle(articleDTO.getTitle());
        article.setId(articleDTO.getArticleId());
        article.setCurrentLikes(articleDTO.getLikesCount());
        article.setCreationTime(articleDTO.getCreationTime());
        return article;
    }

}
