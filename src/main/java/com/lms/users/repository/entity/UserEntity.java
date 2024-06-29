package com.lms.users.repository.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.books.entity.Books;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String fullName;
	
	@Column(unique = true)
	private long phoneNo;
	
	@Column(unique = true)
	@NotEmpty
	private String email;
	
    @NotEmpty
	private String password;
    
    @ManyToMany
    @JsonIgnoreProperties("userEntity")
    private List<Books> bookList ;
    
    
}
