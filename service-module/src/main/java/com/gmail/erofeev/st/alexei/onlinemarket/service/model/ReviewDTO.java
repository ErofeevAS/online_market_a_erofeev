package com.gmail.erofeev.st.alexei.onlinemarket.service.model;

import java.sql.Timestamp;

public class ReviewDTO {
    private Long id;
    private UserDTO user;
    private String content;
    private Timestamp date;
    private Boolean deleted;
    private Boolean hided;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, UserDTO user, String content, Timestamp date, Boolean deleted, Boolean hided) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.deleted = deleted;
        this.hided = hided;
    }

    public ReviewDTO(UserDTO user, String content, Timestamp date, Boolean deleted, Boolean hided) {
        this.user = user;
        this.content = content;
        this.date = date;
        this.deleted = deleted;
        this.hided = hided;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getHided() {
        return hided;
    }

    public void setHided(Boolean hided) {
        this.hided = hided;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", deleted=" + deleted +
                ", hided=" + hided +
                '}';
    }
}


