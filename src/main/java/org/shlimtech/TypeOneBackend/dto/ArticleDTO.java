package org.shlimtech.TypeOneBackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {

    private int articleId;
    private String title;
    private String text;
    private int likesCount;
    private Date creationDate;

}
