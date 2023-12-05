package org.shlimtech.TypeOneBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "type1user")
@Data
@RequiredArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<Article> articles;

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
