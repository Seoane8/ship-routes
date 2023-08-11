package com.shiproutes.backoffice.user.application.search;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class SearchUserQuery implements Query {
    public final String partialUsername;

    public SearchUserQuery(String partialUsername) {
        this.partialUsername = partialUsername;
    }

    public String partialUsername() {
        return partialUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchUserQuery)) return false;
        SearchUserQuery that = (SearchUserQuery) o;
        return Objects.equals(partialUsername, that.partialUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partialUsername);
    }
}
