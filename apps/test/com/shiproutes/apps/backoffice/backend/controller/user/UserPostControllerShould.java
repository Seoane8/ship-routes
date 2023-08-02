package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.apps.backoffice.backend.controller.BackofficeApplicationTestCase;
import org.junit.jupiter.api.Test;

class UserPostControllerShould extends BackofficeApplicationTestCase {

    @Test
    void create_a_new_user() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"6979d8ce-de09-44ad-aeb1-c9f1c9ef631c\", " +
                "\"username\": \"username\", " +
                "\"password\": \"strongPassword\", " +
                "\"email\": \"username@mail.com\" " +
                "}",
            201
        );
    }

    @Test
    void fail_when_password_is_to_short() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"6979d8ce-de09-44ad-aeb1-c9f1c9ef631c\", " +
                "\"username\": \"username\", " +
                "\"password\": \"passwd\", " +
                "\"email\": \"username@mail.com\" " +
                "}",
            400
        );
    }

    @Test
    void fail_when_email_is_invalid() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"6979d8ce-de09-44ad-aeb1-c9f1c9ef631c\", " +
                "\"username\": \"username\", " +
                "\"password\": \"passwd\", " +
                "\"email\": \"username\" " +
                "}",
            400
        );
    }

    @Test
    void fail_when_already_exists_an_user_with_same_username() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"0d77e331-f77d-4804-8cce-8613ded3fe2a\", " +
                "\"username\": \"username\", " +
                "\"password\": \"otherstrongPassword\", " +
                "\"email\": \"otheruser@mail.com\" " +
                "}",
            201
        );

        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"6979d8ce-de09-44ad-aeb1-c9f1c9ef631c\", " +
                "\"username\": \"username\", " +
                "\"password\": \"strongPassword\", " +
                "\"email\": \"username@mail.com\" " +
                "}",
            409
        );
    }

    @Test
    void fail_when_already_exists_an_user_with_same_email() throws Exception {
        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"0d77e331-f77d-4804-8cce-8613ded3fe2a\", " +
                "\"username\": \"otherusername\", " +
                "\"password\": \"otherstrongPassword\", " +
                "\"email\": \"username@mail.com\" " +
                "}",
            201
        );

        assertRequestWithBody(
            "POST", "/users",
            "{" +
                "\"id\": \"6979d8ce-de09-44ad-aeb1-c9f1c9ef631c\", " +
                "\"username\": \"username\", " +
                "\"password\": \"strongPassword\", " +
                "\"email\": \"username@mail.com\" " +
                "}",
            409
        );
    }
}
