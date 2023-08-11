package com.shiproutes.backoffice.user.application.search;

import com.shiproutes.backoffice.user.domain.UserRepository;
import com.shiproutes.shared.domain.Service;

@Service
public class UserSearcher {

    private final UserRepository repository;

    public UserSearcher(UserRepository repository) {
        this.repository = repository;
    }

    public UsersResponse search(String partialUsername) {
        return UsersResponse.from(repository.search(partialUsername));
    }
}
