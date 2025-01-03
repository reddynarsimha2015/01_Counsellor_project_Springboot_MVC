package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.binding.DashboardResponseDTO;
import com.nt.binding.EnquiryDTO;
import com.nt.binding.EnquiryFilterDTO;
import com.nt.entity.CounsellorEntity;
import com.nt.entity.EquiryEntity;
import com.nt.repository.CounsellorRepository;
import com.nt.repository.EnquiryRespository;
@Service
public class EquiryServiceImpl implements EquiryService {
	
	@Autowired
	CounsellorRepository counsellorRep;
	@Autowired
	EnquiryRespository equiryRep;

	@Override
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId) {
		// TODO Auto-generated method stub
		
	 List<EquiryEntity> enqList = equiryRep.findByCounsellorCounsellorId(counsellorId);
	 System.out.println(enqList);
	 //total count
	 long totalCount=enqList.size();
	 //open status count
	 long openStatusCount = enqList.stream().filter(e->"OPENED".equals(e.getEnqStatus())).collect(Collectors.toList()).size();
	 System.out.println("------openStatusCount----"+openStatusCount);
	 //Enrolle Cout
	 long enrolledStatusCount = enqList.stream().filter(e->"ENROLLED".equals(e.getEnqStatus())).collect(Collectors.toList()).size();
	 System.out.println("------enrolledStatusCount----"+enrolledStatusCount);
	//lostcount
	 long lostStatusCount = enqList.stream().filter(e->"LOST".equals(e.getEnqStatus())).collect(Collectors.toList()).size();
	 System.out.println("------lostStatusCount----"+lostStatusCount);
	 DashboardResponseDTO dashboardResp=new DashboardResponseDTO();
	 dashboardResp.setTotalEnqCnt(totalCount);
	 dashboardResp.setOpenEnqCnt(openStatusCount);
	 dashboardResp.setEnrolledEnqCnt(enrolledStatusCount);
	 dashboardResp.setLostEnqCnt(lostStatusCount);
	 return dashboardResp;
	}

	@Override
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId) {
		System.out.print("----------CounsellorId--------"+counsellorId);
		// TODO Auto-generated method stub
		EquiryEntity entity =new EquiryEntity();
		//copy enqDto to entity
		BeanUtils.copyProperties(enqDTO, entity);
		Optional<CounsellorEntity> byId = counsellorRep.findById(counsellorId);
		if(byId.isPresent()) {
			CounsellorEntity counsellor = byId.get();
			entity.setCounsellor(counsellor);
		}
		//save entity
		EquiryEntity savedEntity = equiryRep.save(entity);
		return savedEntity.getEnqId()!=null;
	}

	@Override
	public List<EnquiryDTO> getEnquiry(Integer counsellorId) {
		// TODO Auto-generated method stub
		List<EnquiryDTO> enqDtoList=new ArrayList<>();
		List<EquiryEntity> enqList = equiryRep.findByCounsellorCounsellorId(counsellorId);
		for(EquiryEntity entity:enqList) {
			EnquiryDTO dto=new EnquiryDTO();
			BeanUtils.copyProperties(entity, dto);
			enqDtoList.add(dto);
		}
		return enqDtoList;
	}

	@Override
	public List<EnquiryDTO> getFilterEnquiry(EnquiryFilterDTO filterDto, Integer ConsellorId) {
		// TODO Auto-generated method stub
		EquiryEntity enqEntity=new EquiryEntity();
		if(filterDto.getClassMode()!=null  && !filterDto.getClassMode().equals("")) {
			enqEntity.setClassMode(filterDto.getClassMode());
		}
		if(filterDto.getCourse()!=null  && !filterDto.getCourse().equals("")) {
			enqEntity.setCourse(filterDto.getCourse());
		}
		if(filterDto.getEnqStatus()!=null  && !filterDto.getEnqStatus().equals("")) {
			enqEntity.setEnqStatus(filterDto.getEnqStatus());
		}
		CounsellorEntity counsellorEntity=new CounsellorEntity();
		counsellorEntity.setCounsellorId(ConsellorId);
		
		Example<EquiryEntity> of=Example.of(enqEntity);
		List<EquiryEntity> list = equiryRep.findAll(of);
		System.out.println(of);
		System.out.println("-----list----"+list.size());
		List<EnquiryDTO>  dtoList=new ArrayList<>();
		for(EquiryEntity entity:list) {
			EnquiryDTO dto= new EnquiryDTO();
			BeanUtils.copyProperties(entity, dto);
			System.out.println(dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public EnquiryDTO getEnquirya(Integer enqId) {
		// TODO Auto-generated method stub
		Optional<EquiryEntity> byId = equiryRep.findById(enqId);
		if(byId.isPresent()) {
			EnquiryDTO dto=new EnquiryDTO();
			EquiryEntity entity=byId.get();
		//copy from entity to dto
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

}
