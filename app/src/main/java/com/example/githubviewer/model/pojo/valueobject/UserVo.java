package com.example.githubviewer.model.pojo.valueobject;

public class UserVo {
    private Integer id;
    private String login;
    private String avatarUrl;
    private String type;
    private String name;
    private String company;

    private UserVo(Builder builder) {
        id = builder.id;
        login = builder.login;
        avatarUrl = builder.avatarUrl;
        type = builder.type;
        name = builder.name;
        company = builder.company;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserVo copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.login = copy.login;
        builder.avatarUrl = copy.avatarUrl;
        builder.type = copy.type;
        builder.name = copy.name;
        builder.company = copy.company;
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public static final class Builder {
        private Integer id;
        private String login;
        private String avatarUrl;
        private String type;
        private String name;
        private String company;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder login(String val) {
            login = val;
            return this;
        }

        public Builder avatarUrl(String val) {
            avatarUrl = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder company(String val) {
            company = val;
            return this;
        }

        public UserVo build() {
            return new UserVo(this);
        }
    }
}
