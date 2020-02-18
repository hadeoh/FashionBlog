package com.example.fashionblog.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DesignRequestDTO {

    @NotBlank
    @NotNull
    @Length(max = 50)
    private String title;

    @NotBlank
    @NotNull
    @Length(max = 500)
    private String description;

    @NotNull
    @NotBlank
    private String imageUrl;

    public DesignRequestDTO(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
