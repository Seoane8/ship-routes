package com.shiproutes.backoffice.user.application.upgrade;

import com.shiproutes.backoffice.user.domain.UserId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.command.CommandHandler;

@Service
public class UpgradeUserCommandHandler implements CommandHandler<UpgradeUserCommand> {

    private final UserUpgrader upgrader;

    public UpgradeUserCommandHandler(UserUpgrader upgrader) {
        this.upgrader = upgrader;
    }

    @Override
    public void handle(UpgradeUserCommand command) {
        upgrader.upgrade(new UserId(command.id()));
    }
}
