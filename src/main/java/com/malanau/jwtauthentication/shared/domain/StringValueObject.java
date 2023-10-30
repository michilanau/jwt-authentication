package com.malanau.jwtauthentication.shared.domain;

import java.util.Objects;

public abstract class StringValueObject {
    private final String value;

    public StringValueObject(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValueObject)) {
            return false;
        }
        final StringValueObject that = (StringValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
