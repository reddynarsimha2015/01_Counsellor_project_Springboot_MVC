package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.EquiryEntity;

public interface EnquiryRespository extends JpaRepository<EquiryEntity, Integer> {
	public List<EquiryEntity> findByCounsellorCounsellorId(Integer counsellorID);
}
