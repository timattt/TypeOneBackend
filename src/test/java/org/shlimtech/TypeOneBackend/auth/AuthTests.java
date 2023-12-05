package org.shlimtech.TypeOneBackend.auth;

import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.shlimtech.TypeOneBackend.base.BaseIntegrationTest;

public class AuthTests extends BaseIntegrationTest {

    @Test
    public void userRegisterAndLoginTest() {
        final String name = "tima";
        final String pass = "gggg";
        testAuthHelper.registerUser(name, pass);
        String token = testAuthHelper.loginUser(name, pass);
        Assert.assertFalse(Strings.isNullOrEmpty(token));
    }

}
