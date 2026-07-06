package com.crio.starter.exchange;

import javax.validation.constraints.NotBlank;

public class MemeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String caption;

    @NotBlank
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}