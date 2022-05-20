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
    private String price;
    @Lob
    private byte[] photo;

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

    public String getPrice() {
        return price;
    }

    public Estate setPrice(String price) {
        this.price = price;
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

    public byte[] getPhoto() {
        return photo;
    }

    public Estate setPhoto(byte[] photo) {
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
