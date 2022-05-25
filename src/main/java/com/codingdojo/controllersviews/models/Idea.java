package com.codingdojo.controllersviews.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ideas")
public class Idea {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="user_id")
	 private User uploader;
	 
	 
	 @NotEmpty(message = "Idea name is required!")
	 @Size(min = 5, max = 200, message = "Idea must be at least 3 characters long!")
	 private String name;
	 
	 
	 
	 @Column(updatable=false)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date createdAt;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date updatedAt;
	 
	 
	 @PrePersist
	 protected void onCreate(){
	     this.createdAt = new Date();
	 }
	 @PreUpdate
	 protected void onUpdate(){
	     this.updatedAt = new Date();
	 }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUploader() {
		return uploader;
	}
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	 
	 
	 
	 
	 
}
