package com.drew.sendlogger.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 2, max = 255, message = "First name must be between 2 and 255 characters.")
	private String firstName;
	
	@Size(min = 2, max = 255, message = "Last name must be between 2 and 255 characters.")
	private String lastName;
	
	@NotNull(message = "Email is required.")
	@Email(message = "Invalid Email.")
	private String email;
	
	@Size(min = 8, max = 255, message = "Password must be 8 to 255 characters long.")
	private String password;
	
	@Transient
	@Size(min = 8, max = 255, message = "Confirmed Password must be between 8 and 255 characters long.")
	private String confirm;
	
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	public void OnCreate() { createdAt = new Date(); }
	@PreUpdate
	public void OnUpdate() { updatedAt = new Date(); }
	
	@OneToMany(mappedBy = "user")
	private List<Climb> climb;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name = "likes",
		joinColumns=@JoinColumn(name = "user_id"),
		inverseJoinColumns=@JoinColumn(name = "climb_id")
	)
	private List<Climb> climbs;
	
	public User() {}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
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
	public List<Climb> getclimb() {
		return climb;
	}
	public void setclimb(List<Climb> climb) {
		this.climb = climb;
	}
	public List<Climb> getclimbs() {
		return climbs;
	}
	public void setclimbs(List<Climb> climbs) {
		this.climbs = climbs;
	}
	

}
