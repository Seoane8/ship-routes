package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.apps.backoffice.backend.controller.BackofficeApplicationTestCase;
import org.junit.jupiter.api.Test;

class UserGetControllerShould extends BackofficeApplicationTestCase {

    @Test
    void authenticate_correctly() throws Exception {
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

        assertRequest("GET", "/login?username=username&password=strongPassword", 200);
    }

    @Test
    void fail_when_username_not_exists() throws Exception {
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

        assertRequest("GET", "/login?username=otheruser&password=strongPassword", 404);
    }

    @Test
    void fail_when_password_is_incorrect() throws Exception {
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

        assertRequest("GET", "/login?username=username&password=password", 404);
    }
}
