package com.shiproutes.backoffice.user.application.upgrade;

import com.shiproutes.backoffice.user.domain.User;
import com.shiproutes.backoffice.user.domain.UserId;
import com.shiproutes.backoffice.user.domain.UserRepository;
import com.shiproutes.shared.domain.Service;

@Service
public class UserUpgrader {

    private final UserRepository repository;

    public UserUpgrader(UserRepository repository) {
        this.repository = repository;
    }

    public void upgrade(UserId userId) {
        User user = repository.search(userId).orElseThrow(() -> new UserNotExist(userId));
        user.upgrade();
        repository.save(user);
    }
}
