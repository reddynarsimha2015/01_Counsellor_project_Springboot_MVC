package com.nt.service;

import java.util.List;

import com.nt.binding.DashboardResponseDTO;
import com.nt.binding.EnquiryDTO;
import com.nt.binding.EnquiryFilterDTO;

public interface EquiryService {
public DashboardResponseDTO getDashboardInfo(Integer CounsellorId);
public boolean addEnquiry(EnquiryDTO enqDTO,Integer CounsellorId);
public List<EnquiryDTO> getEnquiry(Integer counsellorId);
public List<EnquiryDTO> getFilterEnquiry(EnquiryFilterDTO filterDto,Integer ConsellorId);
public EnquiryDTO  getEnquirya(Integer enqId);
} 
