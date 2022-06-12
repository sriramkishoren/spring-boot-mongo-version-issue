package org.example.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "documents")
public class Documents {
    @Id
    private String id;
    private String title;
    private String description;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastUpdatedAt;

    @Version
    private Long version;

    public Documents() {
    }

    public Documents(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
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
}