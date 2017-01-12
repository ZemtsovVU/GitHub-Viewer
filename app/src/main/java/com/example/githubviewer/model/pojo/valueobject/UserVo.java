package com.example.githubviewer.model.pojo.valueobject;

public class UserVo {
    private String firstName;
    private String lastName;
    private String age;

    private UserVo(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        age = builder.age;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserVo copy) {
        Builder builder = new Builder();
        builder.firstName = copy.firstName;
        builder.lastName = copy.lastName;
        builder.age = copy.age;
        return builder;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String age;

        private Builder() {
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public UserVo build() {
            return new UserVo(this);
        }
    }
}
