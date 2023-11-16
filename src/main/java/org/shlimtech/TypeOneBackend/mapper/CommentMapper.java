package org.shlimtech.TypeOneBackend.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.shlimtech.TypeOneBackend.model.Comment;
import org.shlimtech.TypeOneBackend.repository.ArticlesRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentMapper {

    private ModelMapper modelMapper;
    private ArticlesRepository articlesRepository;

    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(comment.getId());
        dto.setAuthor(comment.getAuthor());
        dto.setText(comment.getText());
        dto.setArticleId(comment.getArticle().getId());
        dto.setLikesCount(comment.getLikesCount());
        dto.setCreationTime(comment.getCreationTime());
        return dto;
    }

    public Comment commentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setArticle(articlesRepository.getReferenceById(commentDTO.getArticleId()));
        comment.setId(commentDTO.getCommentId());
        comment.setLikesCount(commentDTO.getLikesCount());
        comment.setCreationTime(commentDTO.getCreationTime());
        return comment;
    }

}
