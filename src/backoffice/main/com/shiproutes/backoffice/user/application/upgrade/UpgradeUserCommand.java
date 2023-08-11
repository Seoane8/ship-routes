package com.shiproutes.backoffice.user.application.upgrade;

import com.shiproutes.shared.domain.bus.command.Command;

import java.util.Objects;

public class UpgradeUserCommand implements Command {

    private final String id;

    public UpgradeUserCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpgradeUserCommand)) return false;
        UpgradeUserCommand that = (UpgradeUserCommand) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
