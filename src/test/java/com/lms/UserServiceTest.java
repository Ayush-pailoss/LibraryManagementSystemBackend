package com.lms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lms.repository.AdminRepository;
import com.lms.service.interfaces.IAdminService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	AdminRepository adminRepository;
	
	private IAdminService adminService;

	@Test
	void adminLoginTest() {
		
		adminService.adminLogin(null);
		
	}
}
