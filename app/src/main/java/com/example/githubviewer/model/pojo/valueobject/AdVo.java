package com.example.githubviewer.model.pojo.valueobject;

public class AdVo {
    private String title;
    private String description;

    private AdVo(Builder builder) {
        title = builder.title;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(AdVo copy) {
        Builder builder = new Builder();
        builder.title = copy.title;
        builder.description = copy.description;
        return builder;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static final class Builder {
        private String title;
        private String description;

        private Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public AdVo build() {
            return new AdVo(this);
        }
    }
}
