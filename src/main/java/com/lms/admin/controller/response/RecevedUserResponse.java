package com.lms.admin.controller.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecevedUserResponse {
	private int id;
	private String fullName;
	private long phoneNo;
	private String email;
	
	
	
}
