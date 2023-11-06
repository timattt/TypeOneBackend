package org.shlimtech.TypeOneBackend.repository;

import org.shlimtech.TypeOneBackend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Integer> {
}
