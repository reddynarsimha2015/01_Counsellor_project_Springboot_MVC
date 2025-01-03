package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.CounsellorDTO;
import com.nt.entity.CounsellorEntity;
import com.nt.repository.CounsellorRepository;
@Service
public class ConsellorServiceImpl implements CounsellorService {
	
	@Autowired
	CounsellorRepository counsellorRep;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		// TODO Auto-generated method stub
	CounsellorEntity counsellorEnity = counsellorRep.findByEmailAndPwd(counsellorDTO.getEmail(), counsellorDTO.getPwd());
	if(counsellorEnity!=null) {
		CounsellorDTO counselldto=new CounsellorDTO();
		BeanUtils.copyProperties(counsellorEnity, counselldto);
		return counselldto;
	}
		return null;
		
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		// TODO Auto-generated method stub
		CounsellorEntity entity=new CounsellorEntity();
		BeanUtils.copyProperties(counsellorDTO, entity);
		CounsellorEntity counsellor = counsellorRep.save(entity);
		Integer Id = counsellor.getCounsellorId();
		return null!=Id;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		// TODO Auto-generated method stub
		 CounsellorEntity consellorEnity = counsellorRep.findByEmail(email);
		return consellorEnity==null;
	}

}
