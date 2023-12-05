package org.shlimtech.TypeOneBackend.base;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.shlimtech.TypeOneBackend.dto.ArticleDTO;
import org.shlimtech.TypeOneBackend.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class TestHelpers {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public int createArticle(String userToken, String title, String text) {
        ArticleDTO dto = new ArticleDTO();
        dto.setTitle(title);
        dto.setText(text);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ArticleDTO articleDTO = objectMapper.readValue(mvcResult, ArticleDTO.class);
        Assert.assertEquals(articleDTO.getText(), text);
        Assert.assertEquals(articleDTO.getTitle(), title);
        return articleDTO.getArticleId();
    }

    @SneakyThrows
    public boolean isArticlePresented(String userToken, int articleId) {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ArticleDTO[] articles = objectMapper.readValue(mvcResult, ArticleDTO[].class);
        return List.of(articles).stream().filter(art -> art.getArticleId() == articleId).count() == 1;
    }

    @SneakyThrows
    public void deleteArticle(String userToken, int articleId) {
        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/" + articleId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk());
    }

    @SneakyThrows
    public void expectBadArticleDeletion(String userToken, int articleId) {
        mockMvc.perform(MockMvcRequestBuilders.delete("/articles/" + articleId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().is4xxClientError());
    }

    @SneakyThrows
    public int createComment(String userToken, int articleId, String text) {
        CommentDTO dto = new CommentDTO();
        dto.setArticleId(articleId);
        dto.setText(text);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        CommentDTO commentDTO = objectMapper.readValue(mvcResult, CommentDTO.class);
        Assert.assertEquals(commentDTO.getText(), text);
        return commentDTO.getCommentId();
    }

    @SneakyThrows
    public boolean isCommentPresented(String userToken, int commentId) {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        CommentDTO[] comments = objectMapper.readValue(mvcResult, CommentDTO[].class);
        return List.of(comments).stream().filter(com -> com.getCommentId() == commentId).count() == 1;
    }

    @SneakyThrows
    public void deleteComment(String userToken, int commentId) {
        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/" + commentId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
        ).andExpect(status().isOk());
    }

}
