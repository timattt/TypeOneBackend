package org.shlimtech.TypeOneBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private int articleId;
    private int commentId;
    private String text;
    private String author;
    private Date creationTime;
    private int likesCount;

}
