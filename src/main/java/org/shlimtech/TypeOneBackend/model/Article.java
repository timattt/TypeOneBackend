package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
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

    @ToString.Exclude
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Column(name = "creation_time")
    @CreationTimestamp
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "author_user_id", referencedColumnName = "user_id")
    private User author;

}
