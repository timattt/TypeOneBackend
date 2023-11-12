package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.List;
import java.util.Date;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Column(name = "creation_time")
    @CreationTimestamp
    private Date creationTime;

}
