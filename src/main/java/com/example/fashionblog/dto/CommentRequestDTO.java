package com.example.fashionblog.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentRequestDTO {

    @NotNull
    @NotBlank
    private String displayName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Length(max = 500)
    private String content;

    public CommentRequestDTO(String displayName, String email, String content) {
        this.displayName = displayName;
        this.email = email;
        this.content = content;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
