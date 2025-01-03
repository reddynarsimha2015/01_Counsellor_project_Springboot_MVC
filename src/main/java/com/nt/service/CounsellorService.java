package com.nt.service;

import com.nt.binding.CounsellorDTO;

public interface CounsellorService {
public CounsellorDTO login(CounsellorDTO counsellorDTo);
public boolean register(CounsellorDTO counsellorDTO);
public boolean uniqueEmailCheck(String email);
}
