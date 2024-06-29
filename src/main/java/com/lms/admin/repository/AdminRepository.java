package com.lms.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.admin.repository.entity.AdminEntity;
@Repository
public interface AdminRepository  extends JpaRepository<AdminEntity,Integer>{

   public AdminEntity getByEmail(String email);

   public AdminEntity getByEmailAndPassword(String email , String password);

}
