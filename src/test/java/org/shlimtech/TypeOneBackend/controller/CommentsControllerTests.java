package org.shlimtech.TypeOneBackend.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.shlimtech.TypeOneBackend.base.BaseTest;

public class CommentsControllerTests extends BaseTest {

    @Test
    public void testCommentsCrudForOneUser() {
        final String name = "stas";
        final String pass = "gggg";
        final String articleText = "comintern";
        final String articleTitle = "Ussr";

        // creds
        testAuthHelper.registerUser(name, pass);
        String token = testAuthHelper.loginUser(name, pass);

        // create article
        int articleId = testHelpers.createArticle(token, articleTitle, articleText);
        Assert.assertTrue(testHelpers.isArticlePresented(token, articleId));

        // create comment
        int commentId = testHelpers.createComment(token, articleId, "Gggg");
        Assert.assertTrue(testHelpers.isCommentPresented(token, commentId));

        // delete comment
        testHelpers.deleteComment(token, commentId);
        Assert.assertFalse(testHelpers.isCommentPresented(token, commentId));
    }

}
