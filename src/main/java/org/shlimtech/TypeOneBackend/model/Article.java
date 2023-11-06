package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "article")
@Data
@ToString
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "current_likes")
    private int currentLikes;

    @Column(name = "comments_count")
    private int commentsCount;

    @ToString.Exclude
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

}
