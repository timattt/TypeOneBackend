package org.shlimtech.TypeOneBackend.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.shlimtech.TypeOneBackend.base.BaseTest;

public class ArticlesControllerTests extends BaseTest {
	@Test
	public void testArticlesCRUDForOneUser() {
		final String name = "leonid";
		final String pass = "gggg";
		final String articleText = "comintern";
		final String articleTitle = "Ussr";

		// creds
		testAuthHelper.registerUser(name, pass);
		String token = testAuthHelper.loginUser(name, pass);

		// create
		int articleId = testHelpers.createArticle(token, articleTitle, articleText);
		Assert.assertTrue(testHelpers.isArticlePresented(token, articleId));

		// delete article
		testHelpers.deleteArticle(token, articleId);
		Assert.assertFalse(testHelpers.isArticlePresented(token, articleId));
	}

	@Test
	public void testArticlesCrudForTwoUsers() {
		final String name1 = "ffff";
		final String pass1 = "dfgui";
		final String name2 = "fdsdgre";
		final String pass2 = "rdftgyuijko";

		testAuthHelper.registerUser(name1, pass1);
		testAuthHelper.registerUser(name2, pass2);

		String token1 = testAuthHelper.loginUser(name1, pass1);
		String token2 = testAuthHelper.loginUser(name2, pass2);

		int article1 = testHelpers.createArticle(token1, "ctvybunim", "esdrftgyuhij");

		testHelpers.expectBadArticleDeletion(token2, article1);
	}

}
