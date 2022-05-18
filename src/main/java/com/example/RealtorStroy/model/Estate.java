package com.example.RealtorStroy.model;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
public class Estate {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Lob
    private Blob photo;

    private LocalDateTime createdDate;
    @ManyToOne
    private User publishedBy;

    public Long getId() {
        return id;
    }

    public Estate setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Estate setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Estate setContent(String content) {
        this.content = content;
        return this;
    }

    public Blob getPhoto() {
        return photo;
    }

    public Estate setPhoto(Blob photo) {
        this.photo = photo;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Estate setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public User getPublishedBy() {
        return publishedBy;
    }

    public Estate setPublishedBy(User createdBy) {
        this.publishedBy = createdBy;
        return this;
    }
}
