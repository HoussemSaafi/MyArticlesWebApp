package com.java.userrestapi.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
@Table(name = "stories")
@Entity(name ="stories")
public class Stories {

    //Attributes:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "story")
    private String story;


    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "download_count")
    private Long downloadCount;

    @Type(type = "list-array")
    @Column( name = "view_count_time", columnDefinition = "bigint[]")
    private List<Long> viewCountTime;

    @Column( name = "last_update")
    private LocalDateTime lastUpdate;

    @Type(type = "list-array")
    @Column( name = "download_count_time", columnDefinition = "bigint[]")
    private List<Long> downloadCountTime;

    @Column( name = "last_update_download")
    private LocalDateTime lastUpdateDownload;

    public List<Long> getDownloadCountTime() {
        return downloadCountTime;
    }

    public void setDownloadCountTime(List<Long> downloadCountTime) {
        this.downloadCountTime = downloadCountTime;
    }

    public LocalDateTime getLastUpdateDownload() {
        return lastUpdateDownload;
    }

    public void setLastUpdateDownload(LocalDateTime lastUpdateDownload) {
        this.lastUpdateDownload = lastUpdateDownload;
    }

    public List<Long> getViewCountTime() {
        return viewCountTime;
    }

    public void setViewCountTime(List<Long> viewCountTime) {
        this.viewCountTime = viewCountTime;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    //Constructor, getters and setters
    public Stories(){

    }

    public Stories(String story, LocalDateTime createdAt, String title) {
        this.story = story;
        this.createdAt = createdAt;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setGrantedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
