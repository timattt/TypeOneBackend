package org.shlimtech.TypeOneBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    private int articleId;
    private int commentId;
    private String text;
    private String author;

}
