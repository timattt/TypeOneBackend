package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Data
@ToString
public class Comment {

    @Id
    @Column(name = "commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "articleId", referencedColumnName = "articleId")
    private Article article;

}
