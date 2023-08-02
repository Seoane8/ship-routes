package com.shiproutes.backoffice.user.application.create;

import com.shiproutes.backoffice.user.domain.UserEmail;
import com.shiproutes.backoffice.user.domain.UserId;
import com.shiproutes.backoffice.user.domain.UserRawPassword;
import com.shiproutes.backoffice.user.domain.Username;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserCreator creator;

    public CreateUserCommandHandler(UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateUserCommand command) {
        creator.create(
            new UserId(command.id()),
            new Username(command.username()),
            new UserRawPassword(command.password()),
            new UserEmail(command.email())
        );
    }
}
