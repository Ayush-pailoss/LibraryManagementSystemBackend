package com.lms.users.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterationRequest {
	private String fullName;
	private long phoneNo;
	private String email;
	private String password;
}
