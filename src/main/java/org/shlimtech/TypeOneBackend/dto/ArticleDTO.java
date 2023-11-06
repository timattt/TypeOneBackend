package org.shlimtech.TypeOneBackend.dto;

import lombok.Data;

@Data
public class ArticleDTO {

    private int articleId;
    private String title;
    private String text;
    private int currentLikes;
    private int commentsCount;

}
