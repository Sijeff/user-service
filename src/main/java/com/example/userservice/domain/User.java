package com.example.userservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = User.Builder.class)
public abstract class User {
    abstract String name();
    abstract int age();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "set")
    public static abstract class Builder {
        abstract Builder setName(String value);
        abstract Builder setAge(int age);

        abstract User build();

        @JsonCreator
        public static Builder instance() {
            return builder();
        }
    }
}
