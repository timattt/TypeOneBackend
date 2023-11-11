package org.shlimtech.TypeOneBackend.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.shlimtech.TypeOneBackend.mapper.CommentMapper;
import org.shlimtech.TypeOneBackend.model.Comment;
import org.shlimtech.TypeOneBackend.repository.CommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CommentsService {

    private CommentsRepository commentsRepository;
    private CommentMapper commentMapper;

    @Transactional
    public List<CommentDTO> findAll() {
        return commentsRepository.findAll().stream().map(commentMapper::commentToCommentDTO).toList();
    }

    @Transactional
    public void remove(int id) {
        commentsRepository.deleteById(id);
    }

    @Transactional
    public void add(CommentDTO comment) {
        commentsRepository.save(commentMapper.commentDTOToComment(comment));
    }

    @Transactional
    public void like(int id) {
        Comment comment = commentsRepository.getReferenceById(id);
        comment.setLikesCount(comment.getLikesCount() + 1);
    }
    @Transactional
    public void unlike(int id) {
        Comment comment = commentsRepository.getReferenceById(id);
        comment.setLikesCount(comment.getLikesCount() - 1);
    }
}
