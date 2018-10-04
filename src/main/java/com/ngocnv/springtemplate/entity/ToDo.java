package com.ngocnv.springtemplate.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="todo")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ToDo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	private String toDo;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deadline;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date createdAt;
	
	@LastModifiedDate
	private Date updatedAt;
	
	public ToDo() {
	}
	
	public ToDo(String toDo, Date deadline) {
		this.toDo = toDo;
		this.deadline = deadline;
	}
}
