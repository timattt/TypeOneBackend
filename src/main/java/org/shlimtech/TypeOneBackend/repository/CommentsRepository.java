package org.shlimtech.TypeOneBackend.repository;

import org.shlimtech.TypeOneBackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
}
