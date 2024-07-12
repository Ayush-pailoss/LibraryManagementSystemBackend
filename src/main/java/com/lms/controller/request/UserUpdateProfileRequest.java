package com.lms.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateProfileRequest {
	private String fullName;
	private long phoneNo;
	private String email;
	private String password;
}
