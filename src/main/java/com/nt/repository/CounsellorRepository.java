package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CounsellorEntity;

public interface CounsellorRepository extends JpaRepository<CounsellorEntity, Integer> {
public CounsellorEntity findByEmailAndPwd(String email,String pwd);
public CounsellorEntity findByEmail(String email);
}
