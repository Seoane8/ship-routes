package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.apps.backoffice.backend.controller.BackofficeApplicationTestCase;
import org.junit.jupiter.api.Test;

class UserUpgradePutControllerShould extends BackofficeApplicationTestCase {

    @Test
    void make_admin_an_existent_user() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"0d77e331-f77d-4804-8cce-8613ded3fe2a\", " +
                "\"username\": \"username\", " +
                "\"password\": \"strongPassword\", " +
                "\"email\": \"user@mail.com\" " +
                "}",
            201
        );

        assertRequest("PUT", "/users/0d77e331-f77d-4804-8cce-8613ded3fe2a/upgrade", 202);

        assertResponse(
            "/users?username=username",
            200,
            "[" +
                "{" +
                "\"id\": \"0d77e331-f77d-4804-8cce-8613ded3fe2a\", " +
                "\"username\": \"username\", " +
                "\"email\": \"user@mail.com\", " +
                "\"role\": \"ADMIN\" " +
                "}]"
        );
    }

    @Test
    void fail_when_user_not_exist() throws Exception {
        assertRequest("PUT", "/users/0d77e331-f77d-4804-8cce-8613ded3fe2a/upgrade", 404);
    }
}
