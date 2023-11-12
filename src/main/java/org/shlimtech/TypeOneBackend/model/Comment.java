package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@ToString
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    private Article article;

    @Column(name = "likes_count")
    private int likesCount;

    @Column(name = "creation_time")
    @CreationTimestamp
    private Date creationTime;

}
