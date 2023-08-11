package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.apps.backoffice.backend.controller.BackofficeApplicationTestCase;
import org.junit.jupiter.api.Test;

class UsersGetControllerShould extends BackofficeApplicationTestCase {

    @Test
    void return_empty_list_when_no_user_match_username() throws Exception {
        assertResponse(
            "/users?username=noExistentUsername",
            200,
            "[]"
        );
    }

    @Test
    void return_user_when_match_username() throws Exception {
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

        assertResponse(
            "/users?username=sernam",
            200,
            "[" +
                "{" +
                "\"id\": \"0d77e331-f77d-4804-8cce-8613ded3fe2a\", " +
                "\"username\": \"username\", " +
                "\"email\": \"user@mail.com\", " +
                "\"role\": \"USER\" " +
                "}]"
        );
    }
}
