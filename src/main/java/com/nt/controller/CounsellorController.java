package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.binding.CounsellorDTO;
import com.nt.binding.DashboardResponseDTO;
import com.nt.service.CounsellorService;
import com.nt.service.EquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	CounsellorService counsellorService;
	@Autowired
	EquiryService enquiryService;
	@GetMapping("/")
	public String index(Model model) {
		CounsellorDTO dto=new CounsellorDTO();
		model.addAttribute("counsellor", dto);
		return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		/*
		 * CounsellorDTO dto=new CounsellorDTO(); model.addAttribute("counsellor", dto);
		 */
		return "redirect:/";
	}
	@PostMapping("/login")
	public String handleLogin(HttpServletRequest request,CounsellorDTO dto,Model model) {
	    CounsellorDTO counsellor = counsellorService.login(dto);
		if(counsellor==null) {
			model.addAttribute("emsg", "Invalid Credentials");
			CounsellorDTO counsellordto=new CounsellorDTO();
			model.addAttribute("counsellor", counsellordto);
		    return "index";
		    }
		else {
		Integer counsellorId = counsellor.getCounsellorId();
			System.out.println("--------"+counsellorId);
		//store counsellor id in http session obj
			HttpSession session = request.getSession(true);
		    session.setAttribute("counsellorId",counsellorId);  
			DashboardResponseDTO dashboardDto=	enquiryService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardDto", dashboardDto);
		return "dashboard";
		}
			
	}

	
	@GetMapping("/register")
	public String register(Model model) {
		CounsellorDTO dto=new CounsellorDTO();
		model.addAttribute("counsellor", dto);
		return "register";
	}
	
	@PostMapping("/register")
	public String handeRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor,Model  model) {
		boolean unique = counsellorService.uniqueEmailCheck(counsellor.getEmail());
		if(unique) {
			boolean register=counsellorService.register(counsellor);
			if(register) {
				model.addAttribute("smsg", "Registration Success");	
			}
			else {
				model.addAttribute("emsg","Registration Fail");
			}
			model.addAttribute("smsg", "Registration Success");	
			
		}
		else {
			model.addAttribute("emsg","Enter  unique email");
			//return "register";
		}
		return "register";
	}
}
